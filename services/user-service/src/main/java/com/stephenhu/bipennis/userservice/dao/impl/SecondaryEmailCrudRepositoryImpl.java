package com.stephenhu.bipennis.userservice.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stephenhu.bipennis.model.BO.userservice.SecondaryEmailBO;
import com.stephenhu.bipennis.model.PO.userservice.SecondaryEmailPO;
import com.stephenhu.bipennis.userservice.dao.SecondaryEmailCrudRepository;
import com.stephenhu.bipennis.userservice.mapper.SecondaryEmailMapper;
import com.stephenhu.bipennis.util.DozerStruct.DozerStruct;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.code.Code;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ApiException;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ErrorHandler;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import com.stephenhu.bipennis.util.Regular.EmailRegular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Stephen Hu
 */
@Component
public class SecondaryEmailCrudRepositoryImpl implements SecondaryEmailCrudRepository {
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
    public ResponseResult<SecondaryEmailBO> insert(SecondaryEmailBO secondaryEmailBO) {
        try {
            if (!isRightInsertSecondaryEmailBO(secondaryEmailBO)) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "SecondaryEmailCrudRepositoryImpl.insert");
            }

            SecondaryEmailPO secondaryEmailPO = new DozerStruct<SecondaryEmailBO, SecondaryEmailPO>().transForm(secondaryEmailBO, SecondaryEmailPO.class);

            QueryWrapper<SecondaryEmailPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("u_id", secondaryEmailBO.getUId());

            int count = secondaryEmailMapper.insert(secondaryEmailPO);

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "INSERT");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", secondaryEmailBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "SecondaryEmailCrudRepositoryImpl.insert");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<SecondaryEmailBO> delete(SecondaryEmailBO secondaryEmailBO) {
        try {
            if (secondaryEmailBO.getReId() == null || secondaryEmailBO.getReId().trim().isEmpty()) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "SecondaryEmailCrudRepositoryImpl.delete");
            }

            if (!isExists(secondaryEmailBO)) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            }

            QueryWrapper<SecondaryEmailPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("re_id", secondaryEmailBO.getReId());

            int count = secondaryEmailMapper.delete(queryWrapper);

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "DELETED");
            } else {
                logger.error("Database delete failed for: {}", secondaryEmailBO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "SecondaryEmailCrudRepositoryImpl.delete");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", secondaryEmailBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "SecondaryEmailCrudRepositoryImpl.delete");
            return new ResponseResult<>();
        }
        return null;
    }

    @Override
    public ResponseResult<SecondaryEmailBO> update(SecondaryEmailBO secondaryEmailBO) {
        try {
            if (!isRightUpdateSecondaryEmailBO(secondaryEmailBO)) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "SecondaryEmailCrudRepositoryImpl.update");
            }

            if (!isExists(secondaryEmailBO)) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            }

            SecondaryEmailPO secondaryEmailPO = new DozerStruct<SecondaryEmailBO, SecondaryEmailPO>().transForm(secondaryEmailBO, SecondaryEmailPO.class);

            int count = secondaryEmailMapper.update(secondaryEmailPO, new QueryWrapper<SecondaryEmailPO>().eq("re_id", secondaryEmailBO.getReId()));

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "UPDATED");
            } else {
                logger.error("Database update failed for: {}", secondaryEmailBO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "SecondaryEmailCrudRepositoryImpl.update");
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", secondaryEmailBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "SecondaryEmailCrudRepositoryImpl.update");
            return new ResponseResult<>();
        }
        return null;
    }

    @Override
    public ResponseResult<List<SecondaryEmailBO>> find(SecondaryEmailBO secondaryEmailBO) {
        try {
            if (secondaryEmailBO.getUId() == null || secondaryEmailBO.getUId().trim().isEmpty()) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "SecondaryEmailCrudRepositoryImpl.find");
            }

            QueryWrapper<SecondaryEmailPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("u_id", secondaryEmailBO.getUId());

            List<SecondaryEmailPO> poList = secondaryEmailMapper.selectList(queryWrapper);

            if (poList == null || poList.isEmpty()) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            } else {
                List<SecondaryEmailBO> boList = poList.stream()
                        .map(po -> new DozerStruct<SecondaryEmailPO, SecondaryEmailBO>().transForm(po, SecondaryEmailBO.class))
                        .toList();
                return new ResponseResult<>(Code.OK, "EXISTENCE", boList);
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", secondaryEmailBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "SecondaryEmailCrudRepositoryImpl.find");
            return new ResponseResult<>();
        }
    }

    boolean isRightInsertSecondaryEmailBO(SecondaryEmailBO secondaryEmailBO) {
        if (secondaryEmailBO.getUId() == null || secondaryEmailBO.getUId().trim().isEmpty() ||
                secondaryEmailBO.getReEmail() == null || secondaryEmailBO.getReEmail().trim().isEmpty() ||
                secondaryEmailBO.getReIsPublic() == null || secondaryEmailBO.getReIsPublic().trim().isEmpty()) {
            return false;
        }
        if (!EmailRegular.isEmail(secondaryEmailBO.getReEmail())) {
            return false;
        }
        return true;
    }

    boolean isRightUpdateSecondaryEmailBO(SecondaryEmailBO secondaryEmailBO) {
        if (secondaryEmailBO.getReId() == null || secondaryEmailBO.getReId().trim().isEmpty() ||
                secondaryEmailBO.getUId() == null || secondaryEmailBO.getUId().trim().isEmpty() ||
                secondaryEmailBO.getReIsPublic() == null || secondaryEmailBO.getReIsPublic().trim().isEmpty() ||
                secondaryEmailBO.getReEmail() == null || secondaryEmailBO.getReEmail().trim().isEmpty()) {
            return false;
        }
        if (!EmailRegular.isEmail(secondaryEmailBO.getReEmail())) {
            return false;
        }
        return true;
    }

    public boolean isExists(SecondaryEmailBO secondaryEmailBO) {
        QueryWrapper<SecondaryEmailPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("re_id", secondaryEmailBO.getReId());
        return secondaryEmailMapper.exists(queryWrapper);
    }
}
