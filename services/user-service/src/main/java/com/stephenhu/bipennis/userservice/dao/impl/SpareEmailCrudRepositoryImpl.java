package com.stephenhu.bipennis.userservice.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stephenhu.bipennis.model.BO.userservice.SpareEmailBO;
import com.stephenhu.bipennis.model.PO.userservice.SpareEmailPO;
import com.stephenhu.bipennis.userservice.dao.SpareEmailCrudRepository;
import com.stephenhu.bipennis.userservice.mapper.SpareEmailMapper;
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
public class SpareEmailCrudRepositoryImpl implements SpareEmailCrudRepository {
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
    public ResponseResult<SpareEmailBO> insert(SpareEmailBO spareEmailBO) {
        try {
            if (!isRightInsertSpareEmailBO(spareEmailBO)) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "SpareEmailCrudRepositoryImpl.insert");
            }

            SpareEmailPO spareEmailPO = new DozerStruct<SpareEmailBO, SpareEmailPO>().transForm(spareEmailBO, SpareEmailPO.class);

            QueryWrapper<SpareEmailPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("u_id", spareEmailBO.getUId());

            int count = spareEmailMapper.insert(spareEmailPO);

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
            logger.error("Unexpected error when querying Address for AddressBO: {}", spareEmailBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "SpareEmailCrudRepositoryImpl.insert");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<SpareEmailBO> delete(SpareEmailBO spareEmailBO) {
        try {
            if (spareEmailBO.getSeId() == null || spareEmailBO.getSeId().trim().isEmpty()) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "SpareEmailCrudRepositoryImpl.delete");
            }

            if (!isExists(spareEmailBO)) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            }

            QueryWrapper<SpareEmailPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("se_id", spareEmailBO.getSeId());

            int count = spareEmailMapper.delete(queryWrapper);

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "DELETED");
            } else {
                logger.error("Database delete failed for: {}", spareEmailBO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "SpareEmailCrudRepositoryImpl.delete");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", spareEmailBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "SpareEmailCrudRepositoryImpl.delete");
            return new ResponseResult<>();
        }
        return null;
    }

    @Override
    public ResponseResult<SpareEmailBO> update(SpareEmailBO spareEmailBO) {
        try {
            if (!isRightUpdateSpareEmailBO(spareEmailBO)) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "SecondaryEmailCrudRepositoryImpl.update");
            }

            if (!isExists(spareEmailBO)) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            }

            SpareEmailPO spareEmailPO = new DozerStruct<SpareEmailBO, SpareEmailPO>().transForm(spareEmailBO, SpareEmailPO.class);

            int count = spareEmailMapper.update(spareEmailPO, new QueryWrapper<SpareEmailPO>().eq("se_id", spareEmailBO.getSeId()));

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "UPDATED");
            } else {
                logger.error("Database update failed for: {}", spareEmailBO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "SpareEmailCrudRepositoryImpl.update");
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", spareEmailBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "SpareEmailCrudRepositoryImpl.update");
            return new ResponseResult<>();
        }
        return null;
    }

    @Override
    public ResponseResult<List<SpareEmailBO>> find(SpareEmailBO spareEmailBO) {
        try {
            if (spareEmailBO.getUId() == null || spareEmailBO.getUId().trim().isEmpty()) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "SpareEmailCrudRepositoryImpl.find");
            }

            QueryWrapper<SpareEmailPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("u_id", spareEmailBO.getUId());

            List<SpareEmailPO> poList = spareEmailMapper.selectList(queryWrapper);

            if (poList == null || poList.isEmpty()) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            } else {
                List<SpareEmailBO> boList = poList.stream()
                        .map(po -> new DozerStruct<SpareEmailPO, SpareEmailBO>().transForm(po, SpareEmailBO.class))
                        .toList();
                return new ResponseResult<>(Code.OK, "EXISTENCE", boList);
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", spareEmailBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "SecondaryEmailCrudRepositoryImpl.find");
            return new ResponseResult<>();
        }
    }

    boolean isRightInsertSpareEmailBO(SpareEmailBO spareEmailBO) {
        if (spareEmailBO.getUId() == null || spareEmailBO.getUId().trim().isEmpty() ||
                spareEmailBO.getSeIsPublic() == null || spareEmailBO.getSeIsPublic().trim().isEmpty() ||
                spareEmailBO.getSeEmail() == null || spareEmailBO.getSeEmail().trim().isEmpty()) {
            return false;
        }
        if (!EmailRegular.isEmail(spareEmailBO.getSeEmail())) {
            return false;
        }
        return true;
    }

    boolean isRightUpdateSpareEmailBO(SpareEmailBO spareEmailBO) {
        if (spareEmailBO.getSeId() == null || spareEmailBO.getSeId().trim().isEmpty() ||
                spareEmailBO.getUId() == null || spareEmailBO.getUId().trim().isEmpty() ||
                spareEmailBO.getSeIsPublic() == null || spareEmailBO.getSeIsPublic().trim().isEmpty() ||
                spareEmailBO.getSeEmail() == null || spareEmailBO.getSeEmail().trim().isEmpty()) {
            return false;
        }
        if (!EmailRegular.isEmail(spareEmailBO.getSeEmail())) {
            return false;
        }
        return true;
    }

    public boolean isExists(SpareEmailBO spareEmailBO) {
        QueryWrapper<SpareEmailPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("se_id", spareEmailBO.getSeId());
        return spareEmailMapper.exists(queryWrapper);
    }
}
