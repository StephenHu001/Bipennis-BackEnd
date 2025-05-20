package com.stephenhu.bipennis.authservice.dao.impl;

import com.stephenhu.bipennis.authservice.dao.UserInfoCrudRepository;
import com.stephenhu.bipennis.authservice.mapper.UserInfoMapper;
import com.stephenhu.bipennis.model.BO.authservice.UserInfoBO;
import com.stephenhu.bipennis.model.PO.authservice.UserInfoPO;
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
public final class UserInfoCrudRepositoryImpl implements UserInfoCrudRepository {

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserInfoCrudRepositoryImpl.class);

    private static final String DEFAULT_GENDER = "保密";

    private static final String DEFAULT_AVATAR = "default.png";

    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoCrudRepositoryImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public ResponseResult<UserInfoBO> insertUserInfo(UserInfoBO userInfoBO) {
        String uid = userInfoBO.getUId();
        String lastName = userInfoBO.getLastName();
        String birthdate = userInfoBO.getBirthdate();

        // 参数空值验证
        if (uid == null || lastName == null || birthdate == null) {
            logger.warn("Invalid parameter: UID={}, LastName={}, Birthdate={}",
                    uid, lastName, birthdate);
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT,
                    "UserInfoCrudRepositoryImpl.insertUserInfo");
        }

        // 日期格式验证
        if (!DateRegular.isValidDate(birthdate)) {
            logger.warn("Invalid birthdate parameter: {}", birthdate);
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT,
                    "UserInfoCrudRepositoryImpl.insertUserInfo");
        }

        // 头像和性别字段验证
        if (userInfoBO.getAvatar() != null || userInfoBO.getGender() != null) {
            logger.warn("Non-default fields provided: Avatar={}, Gender={}",
                    userInfoBO.getAvatar(), userInfoBO.getGender());
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT,
                    "UserInfoCrudRepositoryImpl.insertUserInfo");
        }

        try {
            // 仅在未提供时设置默认值
            if (userInfoBO.getAvatar() == null) {
                userInfoBO.setAvatar(DEFAULT_AVATAR);
            }
            if (userInfoBO.getGender() == null) {
                userInfoBO.setGender(DEFAULT_GENDER);
            }

            UserInfoPO userInfoPO = new DozerStruct<UserInfoBO, UserInfoPO>()
                    .transForm(userInfoBO, UserInfoPO.class);

            int count = userInfoMapper.insert(userInfoPO);

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "INSERTED");
            } else {
                logger.error("Database insert failed for: {}", userInfoPO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "UserInfoCrudRepositoryImpl.insertUserInfo");
            }

        } catch (ApiException e) {
            logger.error("DAO error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during insert: {}", userInfoBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL,
                    "UserInfoCrudRepositoryImpl.insertUserInfo");
        }
        return null;
    }
}
