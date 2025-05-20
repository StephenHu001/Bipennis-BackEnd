package com.stephenhu.bipennis.authservice.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stephenhu.bipennis.authservice.dao.SpareEmailCrudRepository;
import com.stephenhu.bipennis.authservice.mapper.SpareEmailMapper;
import com.stephenhu.bipennis.model.BO.authservice.SpareEmailBO;
import com.stephenhu.bipennis.model.PO.authservice.SpareEmailPO;
import com.stephenhu.bipennis.util.DozerStruct.DozerStruct;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ApiException;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.code.Code;
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
public final class SpareEmailCrudRepositoryImpl implements SpareEmailCrudRepository {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(SpareEmailCrudRepositoryImpl.class);

    private final SpareEmailMapper spareEmailMapper;

    @Autowired
    public SpareEmailCrudRepositoryImpl(SpareEmailMapper spareEmailMapper) {
        this.spareEmailMapper = spareEmailMapper;
    }

    @Override
    public ResponseResult<SpareEmailBO> findByEmail(SpareEmailBO spareEmailBO) {
        String email = spareEmailBO.getSeEmail();

        // 参数校验前置
        if (email == null || email.trim().isEmpty()) {
            // 日志记录器
            logger.warn("Invalid email parameter: {}", email);
            //  抛出异常
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "SpareEmailCrudRepositoryImpl.findByEmail");
        }
        //  参数校验
        assert email != null;
        // 参数校验
        if (!EmailRegular.isEmail(email)) {
            // 日志记录器
            logger.warn("Invalid email parameter: {}", email);
            //  抛出异常
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "SpareEmailCrudRepositoryImpl.findByEmail");
        }
        try {
            //  Mapper查出来的PO
            QueryWrapper<SpareEmailPO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("se_email", email);
            SpareEmailPO spareEmailPO = spareEmailMapper.selectOne(queryWrapper);
            // 如果不存在就放回NOT_FOUND
            if (spareEmailPO == null) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            } else {
                spareEmailBO = new DozerStruct<SpareEmailPO, SpareEmailBO>().transForm(spareEmailPO, SpareEmailBO.class);
                //放回结果
                return new ResponseResult<>(Code.OK, "EXISTENCE", spareEmailBO);
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying SpareEmails for email: {}", spareEmailBO.getSeEmail(), e);
            ErrorHandler.throwApiException(Code.INTERNAL, "SpareEmailCrudRepositoryImpl.findByEmail");
            return new ResponseResult<>();
        }
    }
}
