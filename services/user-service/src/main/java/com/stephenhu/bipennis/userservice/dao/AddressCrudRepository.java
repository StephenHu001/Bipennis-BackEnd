package com.stephenhu.bipennis.userservice.dao;

import com.stephenhu.bipennis.model.BO.userservice.AddressBO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;

import java.util.List;

/**
 * @author Stephen Hu
 */
public interface AddressCrudRepository {

    /**
     * 添加地址
     *
     * @param addressBO 添加条件
     * @return ResponseResult<AddressBO>
     */
    ResponseResult<AddressBO> insert(AddressBO addressBO);

    /**
     * 删除地址
     *
     * @param addressBO 删除条件
     * @return ResponseResult<AddressBO>
     */
    ResponseResult<AddressBO> delete(AddressBO addressBO);

    /**
     * 修改地址
     *
     * @param addressBO 修改条件
     * @return ResponseResult<AddressBO>
     */
    ResponseResult<AddressBO> update(AddressBO addressBO);

    /**
     * 查询地址
     *
     * @param addressBO 查询条件
     * @return ResponseResult<AddressBO>
     */
    ResponseResult<List<AddressBO>> find(AddressBO addressBO);

}
