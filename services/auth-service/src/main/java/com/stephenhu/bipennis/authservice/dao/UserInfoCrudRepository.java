package com.stephenhu.bipennis.authservice.dao;

import com.stephenhu.bipennis.model.BO.authservice.UserInfoBO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;

/**
 * @author Stephen Hu
 */
public interface UserInfoCrudRepository {
    /**
     * 插入用户信息
     * @param userInfoBO 用户信息
     * @return ResponseResult<UserInfoBO>
     * */
    ResponseResult<UserInfoBO> insertUserInfo(UserInfoBO userInfoBO);
}
