package com.stephenhu.bipennis.authservice.dao;

import com.stephenhu.bipennis.model.BO.authservice.UserBO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;

/**
 * @author Stephen Hu
 */
public interface UserCrudRepository {
    /**
     * 根据邮箱查询用户信息
     * @param userBO 用户（不可为空）
     * @return 用户（可能为空）
     * @description 查询与主邮箱绑定的信息
     * */
    ResponseResult<UserBO> findByEmail(UserBO userBO);

    /**
     * 根据手机号查询用户信息
     * @param userBO 用户（不可为空）
     * @return 用户（不可能为空）
     * @description 查询与指定手机号绑定的信息
     * */
    ResponseResult<UserBO> findByPhone(UserBO userBO);

    /**
     * 根据用户ID查询用户信息
     * @param userBo 用户ID（不可为空）
     * @return 用户（不可能为空）
     * @description 查询与指定用户ID绑定的信息
     * */
    ResponseResult<UserBO> findByUid(UserBO userBo);
    /**
     * 插入用户信息
     * @param userBO 用户（不可为空）
     * @return 用户（可能为空）
     * */
    ResponseResult<UserBO> insertUser(UserBO userBO);
}
