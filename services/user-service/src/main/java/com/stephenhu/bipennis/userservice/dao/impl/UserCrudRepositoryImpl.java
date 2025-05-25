package com.stephenhu.bipennis.userservice.dao.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stephenhu.bipennis.model.BO.userservice.UserBO;
import com.stephenhu.bipennis.model.PO.userservice.UserPO;
import com.stephenhu.bipennis.userservice.dao.UserCrudRepository;
import com.stephenhu.bipennis.userservice.mapper.UserMapper;
import com.stephenhu.bipennis.util.DozerStruct.DozerStruct;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.code.Code;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ApiException;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ErrorHandler;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import com.stephenhu.bipennis.util.Regular.PasswordRegular;
import com.stephenhu.bipennis.util.Regular.PhoneRegular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * @author Stephen Hu
 */
@Component
public class UserCrudRepositoryImpl implements UserCrudRepository {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserInfoCrudRepositoryImpl.class);

    private final UserMapper userMapper;

    @Autowired
    public UserCrudRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ResponseResult<UserBO> updatePhone(UserBO userBO) {
        try {
            if (!isRightUpdatePhoneUserBO(userBO)) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.updatePhone");
            }

            if (!PhoneRegular.isInternationalPhone(userBO.getUPhone())) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.updatePhone");
            }

            UserPO userPO = new DozerStruct<UserBO, UserPO>().transForm(userBO, UserPO.class);

            QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("u_id", userPO.getUId());

            int count = userMapper.update(userPO, queryWrapper);

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "UPDATED");
            } else {
                logger.error("Database update failed for: {}", userBO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "UserCrudRepositoryImpl.updatePhone");
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", userBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserCrudRepositoryImpl.updatePhone");
            return new ResponseResult<>();
        }
        return null;
    }

    @Override
    public ResponseResult<UserBO> updatePassword(UserBO userBO) {
        try {
            if (!isRightUpdatePasswordUserBO(userBO)) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.updatePassword");
            }

            if (!PasswordRegular.isValidPassword(userBO.getHashPassword())) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.updatePassword");
            }

            userBO.setHashPassword(BCrypt.hashpw(userBO.getHashPassword(), BCrypt.gensalt()));

            UserPO userPO = new DozerStruct<UserBO, UserPO>().transForm(userBO, UserPO.class);

            QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("u_id", userPO.getUId());

            int count = userMapper.update(userPO, queryWrapper);

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "UPDATED");
            } else {
                logger.error("Database update failed for: {}", userBO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "UserCrudRepositoryImpl.updatePassword");
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", userBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserCrudRepositoryImpl.updatePassword");
            return new ResponseResult<>();
        }
        return null;
    }

    @Override
    public ResponseResult<UserBO> find(UserBO userBO) {
        try {
            if (userBO.getUId().isEmpty()) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserCrudRepositoryImpl.find");
            }
            QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("u_id", userBO.getUId());
            UserPO user = userMapper.selectOne(queryWrapper);
            if (user == null) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            } else {
                userBO = new DozerStruct<UserPO, UserBO>().transForm(user, UserBO.class);
                return new ResponseResult<>(Code.OK, "EXISTENCE", userBO);
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", userBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserCrudRepositoryImpl.find");
            return new ResponseResult<>();
        }
    }

    @Override
    public boolean isEmailExist(String email) {
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_email", email);
        return userMapper.selectOne(queryWrapper) != null;
    }

    public boolean isRightUpdatePhoneUserBO(UserBO userBO) {
        return
                // 必须为非null或空白字符串的字段
                (userBO.getUId() != null && !StringUtils.isBlank(userBO.getUId())) &&
                        (userBO.getUPhone() != null && !StringUtils.isBlank(userBO.getUPhone())) &&
                        // 必须为null或空白字符串的字段
                        StringUtils.isBlank(userBO.getHashPassword()) &&
                        StringUtils.isBlank(userBO.getUEmail());
    }

    public boolean isRightUpdatePasswordUserBO(UserBO userBO) {
        return
                // 必须为非null或空白字符串的字段
                (userBO.getUId() != null && !StringUtils.isBlank(userBO.getUId())) &&
                        (userBO.getHashPassword() != null && !StringUtils.isBlank(userBO.getHashPassword())) &&
                        // 必须为null或空白字符串的字段
                        StringUtils.isBlank(userBO.getUPhone()) &&
                        StringUtils.isBlank(userBO.getUEmail());
    }

    public boolean isRightUpdateFindUserBO(UserBO userBO) {
        return
                // 必须为非null或空白字符串的字段
                (userBO.getUId() != null && !StringUtils.isBlank(userBO.getUId())) &&
                        // 必须为null或空白字符串的字段
                        StringUtils.isBlank(userBO.getUPhone()) &&
                        StringUtils.isBlank(userBO.getHashPassword()) &&
                        StringUtils.isBlank(userBO.getUEmail());
    }
}
