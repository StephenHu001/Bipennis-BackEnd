package com.stephenhu.bipennis.userservice.dao.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stephenhu.bipennis.model.BO.userservice.UserInfoBO;
import com.stephenhu.bipennis.model.PO.userservice.UserInfoPO;
import com.stephenhu.bipennis.userservice.dao.UserInfoCrudRepository;
import com.stephenhu.bipennis.userservice.mapper.UserInfoMapper;
import com.stephenhu.bipennis.util.DozerStruct.DozerStruct;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.code.Code;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ApiException;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ErrorHandler;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import com.stephenhu.bipennis.util.Regular.DateRegular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Stephen Hu
 */
@Component
public class UserInfoCrudRepositoryImpl implements UserInfoCrudRepository {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserInfoCrudRepositoryImpl.class);


    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoCrudRepositoryImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public ResponseResult<UserInfoBO> updateName(UserInfoBO userInfoBO) {
        try {
            if (!isRightUpdateNameUserInfoBO(userInfoBO)){
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserInfoCrudRepositoryImpl.updateName");
            }

            UserInfoPO userInfoPO = new DozerStruct<UserInfoBO, UserInfoPO>().transForm(userInfoBO, UserInfoPO.class);

            QueryWrapper<UserInfoPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("u_id", userInfoPO.getUId());

            int count = userInfoMapper.update(userInfoPO, queryWrapper);

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "UPDATED");
            } else {
                logger.error("Database update failed for: {}", userInfoBO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "UserInfoCrudRepositoryImpl.updateName");
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", userInfoBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserInfoCrudRepositoryImpl.updateName");
            return new ResponseResult<>();
        }
        return null;
    }

    @Override
    public ResponseResult<UserInfoBO> updateGender(UserInfoBO userInfoBO) {
        try {
            if (!isRightUpdateGenderUserInfoBO(userInfoBO)){
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserInfoCrudRepositoryImpl.updateGender");
            }

            UserInfoPO userInfoPO = new DozerStruct<UserInfoBO, UserInfoPO>().transForm(userInfoBO, UserInfoPO.class);

            QueryWrapper<UserInfoPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("u_id", userInfoPO.getUId());

            int count = userInfoMapper.update(userInfoPO, queryWrapper);

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "UPDATED");
            } else {
                logger.error("Database update failed for: {}", userInfoBO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "UserInfoCrudRepositoryImpl.updateGender");
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", userInfoBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserInfoCrudRepositoryImpl.updateGender");
            return new ResponseResult<>();
        }
        return null;
    }

    @Override
    public ResponseResult<UserInfoBO> updateAvatar(UserInfoBO userInfoBO) {
        try {
            if (!isRightUpdateAvatarUserInfoBO(userInfoBO)){
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserInfoCrudRepositoryImpl.updateAvatar");
            }

            UserInfoPO userInfoPO = new DozerStruct<UserInfoBO, UserInfoPO>().transForm(userInfoBO, UserInfoPO.class);

            QueryWrapper<UserInfoPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("u_id", userInfoPO.getUId());

            int count = userInfoMapper.update(userInfoPO, queryWrapper);

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "UPDATED");
            } else {
                logger.error("Database update failed for: {}", userInfoBO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "UserInfoCrudRepositoryImpl.updateAvatar");
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", userInfoBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserInfoCrudRepositoryImpl.updateAvatar");
            return new ResponseResult<>();
        }
        return null;
    }

    @Override
    public ResponseResult<UserInfoBO> updateBirthdate(UserInfoBO userInfoBO) {
        try {
            if (!isRightUpdateBirthdateUserInfoBO(userInfoBO)){
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserInfoCrudRepositoryImpl.updateBirthdate");
            }
            if (!DateRegular.isValidDate(userInfoBO.getBirthdate())){
                logger.warn("Invalid birthdate parameter: {}", userInfoBO.getBirthdate());
            }

            UserInfoPO userInfoPO = new DozerStruct<UserInfoBO, UserInfoPO>().transForm(userInfoBO, UserInfoPO.class);

            QueryWrapper<UserInfoPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("u_id", userInfoPO.getUId());

            int count = userInfoMapper.update(userInfoPO, queryWrapper);

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "UPDATED");
            } else {
                logger.error("Database update failed for: {}", userInfoBO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "UserInfoCrudRepositoryImpl.updateBirthdate");
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", userInfoBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserInfoCrudRepositoryImpl.updateBirthdate");
            return new ResponseResult<>();
        }
        return null;
    }

    @Override
    public ResponseResult<UserInfoBO> find(UserInfoBO userInfoBO) {
        try {
            if (userInfoBO.getUId().isEmpty()){
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserInfoCrudRepositoryImpl.find");
            }

            QueryWrapper<UserInfoPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("u_id", userInfoBO.getUId());

            UserInfoPO userInfoPO = userInfoMapper.selectOne(queryWrapper);

            userInfoPO.setAvatar("http://localhost:8081/avatar/"+userInfoPO.getAvatar());

            userInfoBO =  new DozerStruct<UserInfoPO, UserInfoBO>().transForm(userInfoPO, UserInfoBO.class);

            return new ResponseResult<>(Code.OK, "FOUND", userInfoBO);

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", userInfoBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserInfoCrudRepositoryImpl.find");
            return new ResponseResult<>();
        }
    }

    public boolean isRightUpdateNameUserInfoBO(UserInfoBO userInfoBO) {
        return
                // 必须为非null或空白字符串的字段
                (userInfoBO.getUId() != null && !StringUtils.isBlank(userInfoBO.getUId())) &&
                        (userInfoBO.getLastName() != null && !StringUtils.isBlank(userInfoBO.getLastName())) &&
                        // 必须为null或空白字符串的字段
                        StringUtils.isBlank(userInfoBO.getAvatar()) &&
                        StringUtils.isBlank(userInfoBO.getGender()) &&
                        StringUtils.isBlank(userInfoBO.getGenderIsPublic()) &&
                        StringUtils.isBlank(userInfoBO.getBirthdate()) &&
                        StringUtils.isBlank(userInfoBO.getBirthdateIsPublic());
    }
    public boolean isRightUpdateGenderUserInfoBO(UserInfoBO userInfoBO) {
        return
                // 必须为非null或空白字符串的字段
                (userInfoBO.getUId() != null && !StringUtils.isBlank(userInfoBO.getUId())) &&
                        (userInfoBO.getGender() != null && !StringUtils.isBlank(userInfoBO.getGender())) &&
                        (userInfoBO.getGenderIsPublic() != null && !StringUtils.isBlank(userInfoBO.getGenderIsPublic())) &&
                        // 必须为null或空白字符串的字段
                        StringUtils.isBlank(userInfoBO.getAvatar()) &&
                        StringUtils.isBlank(userInfoBO.getLastName()) &&
                        StringUtils.isBlank(userInfoBO.getFirstName()) &&
                        StringUtils.isBlank(userInfoBO.getBirthdate()) &&
                        StringUtils.isBlank(userInfoBO.getBirthdateIsPublic());
    }

    public boolean isRightUpdateAvatarUserInfoBO(UserInfoBO userInfoBO) {
        return
                // 必须为非null或空白字符串的字段
                (userInfoBO.getUId() != null && !StringUtils.isBlank(userInfoBO.getUId())) &&
                        (userInfoBO.getAvatar() != null && !StringUtils.isBlank(userInfoBO.getAvatar())) &&
                        // 必须为null或空白字符串的字段
                        StringUtils.isBlank(userInfoBO.getGender()) &&
                        StringUtils.isBlank(userInfoBO.getGenderIsPublic()) &&
                        StringUtils.isBlank(userInfoBO.getLastName()) &&
                        StringUtils.isBlank(userInfoBO.getFirstName()) &&
                        StringUtils.isBlank(userInfoBO.getBirthdate()) &&
                        StringUtils.isBlank(userInfoBO.getBirthdateIsPublic());
    }

    public boolean isRightUpdateBirthdateUserInfoBO(UserInfoBO userInfoBO) {
        return
                // 必须为非null或空白字符串的字段
                (userInfoBO.getUId() != null && !StringUtils.isBlank(userInfoBO.getUId())) &&
                        (userInfoBO.getBirthdate() != null && !StringUtils.isBlank(userInfoBO.getBirthdate())) &&
                        (userInfoBO.getBirthdateIsPublic() != null && !StringUtils.isBlank(userInfoBO.getBirthdateIsPublic())) &&
                        // 必须为null或空白字符串的字段
                        StringUtils.isBlank(userInfoBO.getGender()) &&
                        StringUtils.isBlank(userInfoBO.getGenderIsPublic()) &&
                        StringUtils.isBlank(userInfoBO.getLastName()) &&
                        StringUtils.isBlank(userInfoBO.getFirstName()) &&
                        StringUtils.isBlank(userInfoBO.getAvatar());
    }

    public boolean isRightUpdateFindUserInfoBO(UserInfoBO userInfoBO) {
        return
                // 必须为非null或空白字符串的字段
                (userInfoBO.getUId() != null && !StringUtils.isBlank(userInfoBO.getUId())) &&
                        // 必须为null或空白字符串的字段
                        StringUtils.isBlank(userInfoBO.getBirthdate()) &&
                        StringUtils.isBlank(userInfoBO.getBirthdateIsPublic()) &&
                        StringUtils.isBlank(userInfoBO.getGender()) &&
                        StringUtils.isBlank(userInfoBO.getGenderIsPublic()) &&
                        StringUtils.isBlank(userInfoBO.getLastName()) &&
                        StringUtils.isBlank(userInfoBO.getFirstName()) &&
                        StringUtils.isBlank(userInfoBO.getAvatar());
    }

}
