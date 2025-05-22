package com.stephenhu.bipennis.userservice.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stephenhu.bipennis.model.BO.userservice.AddressBO;
import com.stephenhu.bipennis.model.PO.userservice.AddressPO;
import com.stephenhu.bipennis.userservice.dao.AddressCrudRepository;
import com.stephenhu.bipennis.userservice.mapper.AddressMapper;
import com.stephenhu.bipennis.util.DozerStruct.DozerStruct;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.code.Code;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ApiException;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ErrorHandler;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Stephen Hu
 */
@Component
public class AddressCrudRepositoryImpl implements AddressCrudRepository {

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(AddressCrudRepositoryImpl.class);


    private final AddressMapper addressMapper;

    @Autowired
    public AddressCrudRepositoryImpl(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public ResponseResult<AddressBO> insert(AddressBO addressBO) {
        try {
            if (!isRightInsertAddressBO(addressBO)) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "AddressCrudRepositoryImpl.insert");
            }
            AddressPO addressPO = new DozerStruct<AddressBO, AddressPO>().transForm(addressBO, AddressPO.class);

            int count = addressMapper.insert(addressPO);

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "INSERTED");
            } else {
                logger.error("Database insert failed for: {}", addressPO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "AddressCrudRepositoryImpl.insert");
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", addressBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AddressCrudRepositoryImpl.insert");
            return new ResponseResult<>();
        }
        return null;
    }

    @Override
    public ResponseResult<AddressBO> delete(AddressBO addressBO) {
        try {

            if (addressBO.getAId() == null || addressBO.getAId().trim().isEmpty()) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "AddressCrudRepositoryImpl.delete");
            }

            if (!isExists(addressBO)) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            }

            QueryWrapper<AddressPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("a_id", addressBO.getAId());

            int count = addressMapper.delete(queryWrapper);

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "DELETED");
            } else {
                logger.error("Database delete failed for: {}", addressBO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "AddressCrudRepositoryImpl.delete");
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", addressBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AddressCrudRepositoryImpl.delete");
            return new ResponseResult<>();
        }
        return null;
    }

    @Override
    public ResponseResult<AddressBO> update(AddressBO addressBO) {
        try {
            if (!isRightUpdateAddressBO(addressBO) || addressBO.getUId() != null || addressBO.getUId().trim().isEmpty()) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "AddressCrudRepositoryImpl.update");
            }

            if (!isExists(addressBO)) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            }

            AddressPO addressPO = new DozerStruct<AddressBO, AddressPO>().transForm(addressBO, AddressPO.class);

            int count = addressMapper.update(addressPO, new QueryWrapper<AddressPO>().eq("a_id", addressBO.getAId()));

            if (count > 0) {
                return new ResponseResult<>(Code.OK, "UPDATED");
            } else {
                logger.error("Database update failed for: {}", addressBO);
                ErrorHandler.throwApiException(Code.DATABASE_QUERY_ERROR,
                        "AddressCrudRepositoryImpl.update");
            }
            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", addressBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AddressCrudRepositoryImpl.update");
            return new ResponseResult<>();
        }
        return null;
    }

    @Override
    public ResponseResult<List<AddressBO>> find(AddressBO addressBO) {
        try {

            if (addressBO.getUId() == null || addressBO.getUId().trim().isEmpty()) {
                ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "AddressCrudRepositoryImpl.delete");
            }
            QueryWrapper<AddressPO> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("u_id", addressBO.getUId());

            List<AddressPO> poList = addressMapper.selectList(queryWrapper);

            if (poList == null || poList.isEmpty()) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            } else {
                List<AddressBO> boList = poList.stream()
                        .map(po -> new DozerStruct<AddressPO, AddressBO>().transForm(po, AddressBO.class))
                        .toList();
                return new ResponseResult<>(Code.OK, "EXISTENCE", boList);
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying Address for AddressBO: {}", addressBO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AddressCrudRepositoryImpl.find");
            return new ResponseResult<>();
        }
    }

    private Boolean isRightInsertAddressBO(AddressBO addressBO) {
        return addressBO.getUId() != null &&
                !addressBO.getUId().trim().isEmpty() &&
                addressBO.getAIsPublic() != null &&
                !addressBO.getAIsPublic().trim().isEmpty() &&
                addressBO.getLatitude() != null &&
                !addressBO.getLatitude().trim().isEmpty() &&
                addressBO.getLongitude() != null &&
                !addressBO.getLongitude().trim().isEmpty() &&
                addressBO.getAddressLabel() != null &&
                !addressBO.getAddressLabel().trim().isEmpty();
    }

    private Boolean isRightUpdateAddressBO(AddressBO addressBO) {
        return addressBO.getAIsPublic() != null &&
                !addressBO.getAIsPublic().trim().isEmpty() &&
                addressBO.getLatitude() != null &&
                !addressBO.getLatitude().trim().isEmpty() &&
                addressBO.getLongitude() != null &&
                !addressBO.getLongitude().trim().isEmpty() &&
                addressBO.getAddressLabel() != null &&
                !addressBO.getAddressLabel().trim().isEmpty();
    }

    private Boolean isExists(AddressBO addressBO) {
        QueryWrapper<AddressPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a_id", addressBO.getAId());
        return addressMapper.exists(queryWrapper);
    }
}
