package com.stephenhu.bipennis.authservice.dao;

import com.stephenhu.bipennis.model.BO.authservice.SecondaryEmailBO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;

/**
 * @author Stephen Hu
 */
public interface SecondaryEmailCrudRepository {
    /**
     * 根据辅助邮箱查询备用邮箱实体
     * @param secondaryEmailBO 辅助邮箱实体
     * @return ResponseResult<SecondaryEmailBO>
     * @description 查询辅助邮箱
     */
    ResponseResult<SecondaryEmailBO> findByEmail(SecondaryEmailBO secondaryEmailBO);


}
