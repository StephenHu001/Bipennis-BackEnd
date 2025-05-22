package com.stephenhu.bipennis.userservice.dao;

import com.stephenhu.bipennis.model.BO.userservice.UserBO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;

/**
 * @author Stephen Hu
 */
public interface UserCrudRepository {
    /**
     * 修改用户手机号
     *
     * @param userBO 修改条件
     * @return ResponseResult<UserBO>
     * */
    ResponseResult<UserBO> updatePhone(UserBO userBO);

    /**
     * 修改密码
     *
     * @param userBO 修改条件
     * @return ResponseResult<UserBO>
     * */
    ResponseResult<UserBO> updatePassword(UserBO userBO);

    /**
     * 查找
     *
     * @param userBO 查找条件
     * @return ResponseResult<UserBO>
     * */
    ResponseResult<UserBO> find(UserBO userBO);
}
