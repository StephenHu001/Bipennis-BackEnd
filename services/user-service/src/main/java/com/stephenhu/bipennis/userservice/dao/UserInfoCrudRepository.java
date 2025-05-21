package com.stephenhu.bipennis.userservice.dao;

import com.stephenhu.bipennis.model.BO.userservice.UserInfoBO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;

/**
 * @author Stephen Hu
 */
public interface UserInfoCrudRepository {
    /**
     * 更新姓名
     *
     * @param userInfoBO 更新条件
     * @return ResponseResult<UserInfoBO>
     * */
    ResponseResult<UserInfoBO> updateName(UserInfoBO userInfoBO);
    /**
     * 更新性别
     *
     * @param userInfoBO 更新条件
     * @return ResponseResult<UserInfoBO>
     * */
    ResponseResult<UserInfoBO> updateGender(UserInfoBO userInfoBO);
    /**
     * 更新照片
     *
     * @param userInfoBO 更新条件
     * @return ResponseResult<UserInfoBO>
     * */
    ResponseResult<UserInfoBO> updateAvatar(UserInfoBO userInfoBO);
    /**
     * 更新生日
     *
     * @param userInfoBO 更新条件
     * @return ResponseResult<UserInfoBO>
     * */
    ResponseResult<UserInfoBO> updateBirthdate(UserInfoBO userInfoBO);
    /**
     * 查找
     *
     * @param userInfoBO 查找条件
     * @return ResponseResult<UserInfoBO>
     * */
    ResponseResult<UserInfoBO> find(UserInfoBO userInfoBO);

}
