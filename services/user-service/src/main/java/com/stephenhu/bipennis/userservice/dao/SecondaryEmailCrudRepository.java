package com.stephenhu.bipennis.userservice.dao;

import com.stephenhu.bipennis.model.BO.userservice.SecondaryEmailBO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import java.util.List;

/**
 * @author Stephen Hu
 */
public interface SecondaryEmailCrudRepository {
    /**
     * 添加辅助邮箱
     *
     * @param secondaryEmailBO 添加条件
     * @return ResponseResult<AddressBO>
     * */
    ResponseResult<SecondaryEmailBO> insert(SecondaryEmailBO secondaryEmailBO);

    /**
     * 删除辅助邮箱
     *
     * @param secondaryEmailBO 删除条件
     * @return ResponseResult<AddressBO>
     * */
    ResponseResult<SecondaryEmailBO> delete(SecondaryEmailBO secondaryEmailBO);
    /**
     * 修改辅助邮箱
     *
     * @param secondaryEmailBO 修改条件
     * @return ResponseResult<AddressBO>
     * */
    ResponseResult<SecondaryEmailBO> update(SecondaryEmailBO secondaryEmailBO);
    /**
     * 查询辅助邮箱
     *
     * @param secondaryEmailBO 查询条件
     * @return ResponseResult<AddressBO>
     * */
    ResponseResult<List<SecondaryEmailBO>> find(SecondaryEmailBO secondaryEmailBO);
}
