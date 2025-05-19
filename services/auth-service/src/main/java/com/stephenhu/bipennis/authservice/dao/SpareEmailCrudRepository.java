package com.stephenhu.bipennis.authservice.dao;

import com.stephenhu.bipennis.model.BO.authservice.SpareEmailBO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;


/**
 * @author Stephen Hu
 */
public interface SpareEmailCrudRepository {
    /**
     * 根据备用邮箱查询备用邮箱实体
     * @param spareEmailBO 备用邮箱实体
     * @return ResponseResult<SpareEmailBO>
     * @description 查询与指定备用邮箱绑定的备用邮箱信息
     */
    ResponseResult<SpareEmailBO> findByEmail(SpareEmailBO spareEmailBO);

}
