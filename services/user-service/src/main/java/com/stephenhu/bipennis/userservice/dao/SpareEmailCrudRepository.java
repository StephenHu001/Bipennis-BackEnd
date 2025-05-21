package com.stephenhu.bipennis.userservice.dao;

import com.stephenhu.bipennis.model.BO.userservice.SpareEmailBO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;

import java.util.List;

/**
 * @author Stephen Hu
 */
public interface SpareEmailCrudRepository {
    /**
     * 添加备用邮箱
     *
     * @param spareEmailBO 添加条件
     * @return ResponseResult<SpareEmailBO>
     * */
    ResponseResult<SpareEmailBO> insert(SpareEmailBO spareEmailBO);
    /**
     * 删除备用邮箱
     *
     * @param spareEmailBO 删除条件
     * @return ResponseResult<SpareEmailBO>
     * */
    ResponseResult<SpareEmailBO> delete(SpareEmailBO spareEmailBO);
    /**
     * 更新备用邮箱
     *
     * @param spareEmailBO 更新条件
     * @return ResponseResult<SpareEmailBO>
     * */
    ResponseResult<SpareEmailBO> update(SpareEmailBO spareEmailBO);
    /**
     * 查找备用邮箱
     *
     * @param spareEmailBO 查找条件
     * @return ResponseResult<List<SpareEmailBO>>
     * */
    ResponseResult<List<SpareEmailBO>> find(SpareEmailBO spareEmailBO);
}
