package com.stephenhu.bipennis.authservice.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import com.stephenhu.bipennis.authservice.service.AuthService;
import com.stephenhu.bipennis.authservice.dao.SecondaryEmailCrudRepository;
import com.stephenhu.bipennis.authservice.dao.UserCrudRepository;
import com.stephenhu.bipennis.authservice.dao.UserInfoCrudRepository;
import com.stephenhu.bipennis.authservice.dao.impl.SpareEmailCrudRepositoryImpl;
import com.stephenhu.bipennis.model.BO.authservice.SecondaryEmailBO;
import com.stephenhu.bipennis.model.BO.authservice.SpareEmailBO;
import com.stephenhu.bipennis.model.BO.authservice.UserBO;
import com.stephenhu.bipennis.model.BO.authservice.UserInfoBO;
import com.stephenhu.bipennis.model.DTO.autherservice.LoginDTO;
import com.stephenhu.bipennis.model.DTO.autherservice.RegisterDTO;
import com.stephenhu.bipennis.util.DozerStruct.DozerStruct;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.code.Code;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ApiException;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ErrorHandler;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import com.stephenhu.bipennis.util.Regular.EmailRegular;
import com.stephenhu.bipennis.util.Regular.PhoneRegular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Stephen Hu
 */
@Service
public final class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(SpareEmailCrudRepositoryImpl.class);

    private final SpareEmailCrudRepositoryImpl spareEmailCrudRepository;

    private final UserCrudRepository userCrudRepository;

    private final SecondaryEmailCrudRepository secondaryEmailCrudRepository;

    private final UserInfoCrudRepository userInfoCrudRepository;

    @Autowired
    public AuthServiceImpl(SpareEmailCrudRepositoryImpl spareEmailCrudRepository,
                           UserCrudRepository userCrudRepository,
                           SecondaryEmailCrudRepository secondaryEmailCrudRepository,
                           UserInfoCrudRepository userInfoCrudRepository) {
        this.spareEmailCrudRepository = spareEmailCrudRepository;
        this.userCrudRepository = userCrudRepository;
        this.secondaryEmailCrudRepository = secondaryEmailCrudRepository;
        this.userInfoCrudRepository = userInfoCrudRepository;
    }

    @Override
    public ResponseResult<LoginDTO> login(LoginDTO loginDTO) {
        // 获取参数并合并校验
        String account = loginDTO.getAccount();
        String originPassword = loginDTO.getOriginPassword();
        // 参数统一校验
        if (account == null || account.trim().isEmpty() ||
                originPassword == null || originPassword.trim().isEmpty()) {
            logger.warn("Invalid login parameters: account={}, originPassword={}", account, originPassword);
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "Invalid login parameters");
        }
        if (!EmailRegular.isEmail(account)) {
            if (!PhoneRegular.isInternationalPhone(account)) {
                logger.warn("Invalid account parameter: {}", account);
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "Invalid login parameters");
            }
        }
        String uId;
        String hashPassword;
        try {
            // 查询用户信息
            ResponseResult<UserBO> userResponse = findUserByAccount(account);
            // 处理查询结果
            if (!userResponse.getCode().equals(Code.OK)) {
                if (userResponse.getCode().equals(Code.NOT_FOUND)) {
                    return new ResponseResult<>(Code.NOT_FOUND, "User not found");
                }
                // 系统错误直接抛出
                ErrorHandler.throwApiException(Code.INTERNAL, "AuthServiceImpl.login");
            }
            // 获取用户数据
            UserBO userBO = userResponse.getData();
            uId = userBO.getUId();
            hashPassword = userBO.getHashPassword();
            // 密码验证
            if (!BCrypt.checkpw(originPassword, hashPassword)) {
                logger.warn("Password mismatch for user: {}", account);
                return new ResponseResult<>(Code.NOT_FOUND, "User not found");
            }
            logger.info("Login successful for user: {}", account);
            return new ResponseResult<>(Code.OK, "LOGIN OK");

        } catch (ApiException e) {
            logger.error("Business error during login: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when login: {}", loginDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthServiceImpl.login");
            // 抛出异常后无需返回
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<RegisterDTO> register(RegisterDTO registerDTO) {
        try {
            if (isRight(registerDTO)) {
                logger.warn("Invalid register parameters: {}", registerDTO);
                return new ResponseResult<>(Code.ALREADY_EXISTS, "USER EXISTS");
            }

            UserBO userBO = new DozerStruct<RegisterDTO, UserBO>().transForm(registerDTO, UserBO.class);

            if (userCrudRepository.insertUser(userBO).getCode().equals(Code.OK)) {

                UserInfoBO userInfoBO = new DozerStruct<RegisterDTO, UserInfoBO>().transForm(registerDTO, UserInfoBO.class);

                String uId = userCrudRepository.findByEmail(userBO).getData().getUId();

                userInfoBO.setUId(uId);

                if (userInfoCrudRepository.insertUserInfo(userInfoBO).getCode().equals(Code.OK)) {

                    logger.info("Register successful for user: {}", registerDTO);
                    return new ResponseResult<>(Code.OK, "REGISTER OK");
                } else {
                    logger.warn("Register failed for user: {}", registerDTO);
                    ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR, "AuthServiceImpl.login");
                }
            } else {
                logger.warn("Register failed for user: {}", registerDTO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR, "AuthServiceImpl.login");
            }

        } catch (ApiException e) {
            logger.error("Business error during register: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when register: {}", registerDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthServiceImpl.register");
            // 抛出异常后无需返回
            return new ResponseResult<>();
        }
        return null;
    }

    /**
     * 公共方法
     */
    private ResponseResult<UserBO> findUserByAccount(String account) {
        // 创建复用的UserBO
        UserBO userBO = new UserBO();
        // 手机号登录
        if (PhoneRegular.isInternationalPhone(account)) {
            userBO.setUPhone(account);
            return userCrudRepository.findByPhone(userBO);
        }
        // 邮箱登录
        if (EmailRegular.isEmail(account)) {
            userBO.setUEmail(account);
            ResponseResult<UserBO> result = userCrudRepository.findByEmail(userBO);
            // 主邮箱未找到时检查备用邮箱
            if (result.getCode().equals(Code.NOT_FOUND)) {
                SpareEmailBO spareEmailBO = new SpareEmailBO();
                spareEmailBO.setSeEmail(account);
                ResponseResult<SpareEmailBO> spareResult = spareEmailCrudRepository.findByEmail(spareEmailBO);
                if (spareResult.getCode().equals(Code.OK)) {
                    // 通过备用邮箱获取用户ID后查询主信息
                    userBO.setUId(spareResult.getData().getUId());
                    return userCrudRepository.findByUid(userBO);
                }
                return new ResponseResult<>(Code.NOT_FOUND, "User not found");
            }
            // 返回主邮箱查询结果
            return result;
        }
        // 账号类型无效
        return new ResponseResult<>(Code.INVALID_ARGUMENT, "Invalid account type");
    }

    private boolean isRight(RegisterDTO registerDTO) {
        if (registerDTO.getLastName() == null ||
                registerDTO.getBirthdate() == null ||
                registerDTO.getHashPassword() == null ||
                registerDTO.getUEmail() == null ||
                registerDTO.getUPhone() == null
        ) {
            logger.warn("SomeThing can't be null", registerDTO);
            return false;
        }

        if (checkAccountUnique(registerDTO)) {
            logger.warn("Account already exists: {}", registerDTO);
            return false;
        }
        return true;
    }

    private boolean checkAccountUnique(RegisterDTO registerDTO) {
        String email = registerDTO.getUEmail();
        String phone = registerDTO.getUPhone();
        // 主邮箱校验
        if (isEmailExists(email)) {
            return false;
        }
        // 手机号校验
        if (isPhoneExists(phone)) {
            return false;
        }
        // 备用邮箱校验
        if (isSpareEmailExists(email)) {
            return false;
        }
        // 次要邮箱校验
        if (isSecondaryEmailExists(email)) {
            return false;
        }
        return true;
    }

    private boolean isEmailExists(String email) {
        UserBO userBO = new UserBO();
        userBO.setUEmail(email);
        return Code.OK.equals(userCrudRepository.findByEmail(userBO).getCode());
    }

    private boolean isPhoneExists(String phone) {
        UserBO userBO = new UserBO();
        userBO.setUPhone(phone);
        return Code.OK.equals(userCrudRepository.findByPhone(userBO).getCode());
    }

    private boolean isSpareEmailExists(String email) {
        SpareEmailBO spareEmailBO = new SpareEmailBO();
        spareEmailBO.setSeEmail(email);
        return Code.OK.equals(spareEmailCrudRepository.findByEmail(spareEmailBO).getCode());
    }

    private boolean isSecondaryEmailExists(String email) {
        SecondaryEmailBO secondaryEmailBO = new SecondaryEmailBO();
        secondaryEmailBO.setReEmail(email);
        return Code.OK.equals(secondaryEmailCrudRepository.findByEmail(secondaryEmailBO).getCode());
    }

}
