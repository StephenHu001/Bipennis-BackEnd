package com.stephenhu.bipennis.authservice.dao.impl;

import cn.dev33.satoken.secure.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stephenhu.bipennis.authservice.mapper.UserMapper;
import com.stephenhu.bipennis.authservice.dao.UserCrudRepository;
import com.stephenhu.bipennis.model.BO.authservice.UserBO;
import com.stephenhu.bipennis.model.DO.authservice.UserDO;
import com.stephenhu.bipennis.model.PO.authservice.UserPO;
import com.stephenhu.bipennis.util.DozerStruct.DozerStruct;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.code.Code;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ApiException;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ErrorHandler;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import com.stephenhu.bipennis.util.Regular.EmailRegular;
import com.stephenhu.bipennis.util.Regular.NumberRegular;
import com.stephenhu.bipennis.util.Regular.PasswordRegular;
import com.stephenhu.bipennis.util.Regular.PhoneRegular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Stephen Hu
 */
@Component
public final class UserCrudRepositoryImpl implements UserCrudRepository {

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserCrudRepositoryImpl.class);

    private final UserMapper userMapper;

    @Autowired
    public UserCrudRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ResponseResult<UserBO> findByEmail(UserBO userBO) {
        String email = userBO.getUEmail();

        // 参数校验前置
        if (email == null || email.trim().isEmpty()) {
            logger.warn("Invalid email parameter: {}", email);
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.findByEmail");
        }

        assert email != null;

        if (!EmailRegular.isEmail(email)) {
            logger.warn("Invalid email parameter: {}", email);
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.findByEmail");
        }
        try {
            QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("u_email", email);
            UserPO userPO = userMapper.selectOne(queryWrapper);

            if (userPO == null) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            } else {
                UserDO userDO = new DozerStruct<UserPO, UserDO>().transForm(userPO, UserDO.class);

                userBO = new DozerStruct<UserDO, UserBO>().transForm(userDO, UserBO.class);
                return new ResponseResult<>(Code.OK, "EXISTENCE", userBO);
            }
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying UserEmails for email: {}", userBO.getUEmail(), e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserCrudRepositoryImpl.findByEmail");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<UserBO> findByPhone(UserBO userBO) {
        String phone = userBO.getUPhone();

        // 参数校验前置
        if (phone == null || phone.trim().isEmpty()) {
            logger.warn("Invalid phone parameter: {}", phone);
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.findByPhone");
        }

        assert phone != null;

        if (!PhoneRegular.isInternationalPhone(phone)) {
            logger.warn("Invalid phone parameter: {}", phone);
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.findByPhone");
        }
        try {
            QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("u_phone", phone);
            UserPO userPO = userMapper.selectOne(queryWrapper);

            if (userPO == null) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            } else {
                UserDO userDO = new DozerStruct<UserPO, UserDO>().transForm(userPO, UserDO.class);
                userBO = new DozerStruct<UserDO, UserBO>().transForm(userDO, UserBO.class);

                return new ResponseResult<>(Code.OK, "EXISTENCE", userBO);
            }
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying UserPhone for phone: {}", userBO.getUPhone(), e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserCrudRepositoryImpl.findByPhone");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<UserBO> findByUid(UserBO userBO) {
        String uId = userBO.getUId();

        // 参数校验前置
        if (uId == null || uId.trim().isEmpty()) {
            logger.warn("Invalid uId parameter: {}", uId);
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.findByUid");
        }

        assert uId != null;

        if (!NumberRegular.isUnsignedPositiveInteger(uId)) {
            logger.warn("Invalid uId parameter: {}", uId);
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.findByUid");
        }
        try {
            QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("u_id", uId);
            UserPO userPO = userMapper.selectOne(queryWrapper);

            if (userPO == null) {
                ErrorHandler.throwApiException(Code.NOT_FOUND, "UserCrudRepositoryImpl.findByUid");
                return new ResponseResult<>();
            } else {

                UserDO userDO = new DozerStruct<UserPO, UserDO>().transForm(userPO, UserDO.class);
                userBO = new DozerStruct<UserDO, UserBO>().transForm(userDO, UserBO.class);
                return new ResponseResult<>(Code.OK, "EXISTENCE", userBO);
            }
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying UserEmails for email: {}", userBO.getUId(), e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserCrudRepositoryImpl.findByUid");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<UserBO> insertUser(UserBO userBO) {
        String uEmail = userBO.getUEmail();
        String uPhone = userBO.getUPhone();
        String hashPassword = userBO.getHashPassword();

        if (uEmail == null || uEmail.trim().isEmpty() || uPhone == null || uPhone.trim().isEmpty() || hashPassword == null || hashPassword.trim().isEmpty()) {
            logger.warn("null parameter: {},{},{}", uEmail, uPhone, hashPassword);
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.insertUser");
        }

        assert uEmail != null;
        if (!EmailRegular.isEmail(uEmail)) {
            logger.warn("Invalid email parameter: {}", uEmail);
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.insertUser");
        }
        assert uPhone != null;
        if (!PhoneRegular.isInternationalPhone(uPhone)) {
            logger.warn("Invalid phone parameter: {}", uPhone);
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.insertUser");
        }
        assert hashPassword != null;
        if (!PasswordRegular.isValidPassword(hashPassword)) {
            logger.warn("Invalid password parameter: {}", hashPassword);
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.insertUser");
        }
        try {
            userBO.setHashPassword(BCrypt.hashpw(hashPassword, BCrypt.gensalt()));
            UserPO userPO = new DozerStruct<UserBO, UserPO>().transForm(userBO, UserPO.class);
            int count = userMapper.insert(userPO);
            if (count > 0) {
                return new ResponseResult<>(Code.OK, "INSERTED", userBO);
            } else {
                logger.error("Database insert failed for: {}", userPO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "UserInfoCrudRepositoryImpl.insertUserInfo");
            }
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying UserEmails for email: {}", userBO.getUId(), e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserCrudRepositoryImpl.findByUid");
            return new ResponseResult<>();
        }
        return null;
    }
}
