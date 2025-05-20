package com.stephenhu.bipennis.authservice.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stephenhu.bipennis.authservice.dao.SecondaryEmailCrudRepository;
import com.stephenhu.bipennis.authservice.mapper.SecondaryEmailMapper;
import com.stephenhu.bipennis.model.BO.authservice.SecondaryEmailBO;
import com.stephenhu.bipennis.model.PO.authservice.SecondaryEmailPO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.code.Code;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ApiException;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ErrorHandler;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import com.stephenhu.bipennis.util.Regular.EmailRegular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Stephen Hu
 */
@Component
public final class SecondaryEmailCrudRepositoryImpl implements SecondaryEmailCrudRepository {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(SecondaryEmailCrudRepositoryImpl.class);

    private final SecondaryEmailMapper secondaryEmailMapper;

    @Autowired
    public SecondaryEmailCrudRepositoryImpl(SecondaryEmailMapper secondaryEmailMapper) {
        this.secondaryEmailMapper = secondaryEmailMapper;
    }

    @Override
    public ResponseResult<SecondaryEmailBO> findByEmail(SecondaryEmailBO secondaryEmailBO) {
        String email = secondaryEmailBO.getReEmail();

        // 参数校验前置
        if (email == null || email.trim().isEmpty()) {
            // 日志记录器
            logger.warn("Invalid email parameter: {}", email);
            //  抛出异常
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "SecondaryEmailCrudRepositoryImpl.findByEmail");
        }
        //  参数校验
        assert email != null;
        // 参数校验
        if (!EmailRegular.isEmail(email)) {
            // 日志记录器
            logger.warn("Invalid email parameter: {}", email);
            //  抛出异常
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "SecondaryEmailCrudRepositoryImpl.findByEmail");
        }
        try {
            //  Mapper查出来的PO
            QueryWrapper<SecondaryEmailPO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("re_email", email);
            SecondaryEmailPO secondaryEmailPO = secondaryEmailMapper.selectOne(queryWrapper);
            // 如果不存在就放回NOT_FOUND
            if (secondaryEmailPO == null) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            } else {
                return new ResponseResult<>(Code.OK, "EXISTENCE");
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying SecondaryEmail for email: {}", secondaryEmailBO.getReEmail(), e);
            ErrorHandler.throwApiException(Code.INTERNAL, "SecondaryEmailCrudRepositoryImpl.findByEmail");
            return new ResponseResult<>();
        }
    }
}
