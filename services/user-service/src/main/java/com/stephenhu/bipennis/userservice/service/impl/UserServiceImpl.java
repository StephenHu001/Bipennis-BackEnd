package com.stephenhu.bipennis.userservice.service.impl;

import com.stephenhu.bipennis.model.BO.userservice.*;
import com.stephenhu.bipennis.model.DTO.userservice.*;
import com.stephenhu.bipennis.userservice.dao.*;
import com.stephenhu.bipennis.userservice.service.UserService;
import com.stephenhu.bipennis.userservice.service.publicmethod.PublicMethod;
import com.stephenhu.bipennis.util.DozerStruct.DozerStruct;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.code.Code;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ApiException;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ErrorHandler;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import com.stephenhu.bipennis.util.Regular.EmailRegular;
import com.stephenhu.bipennis.util.Regular.PasswordRegular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * @author Stephen Hu
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${upload.path}")
    private String uploadPath;

    private final AddressCrudRepository addressCrudRepository;

    private final SecondaryEmailCrudRepository secondaryEmailCrudRepository;

    private final SpareEmailCrudRepository spareEmailCrudRepository;

    private final UserCrudRepository userCrudRepository;

    private final UserInfoCrudRepository userInfoCrudRepository;

    private final PublicMethod publicMethod;

    @Autowired
    public UserServiceImpl(AddressCrudRepository addressCrudRepository, SecondaryEmailCrudRepository secondaryEmailCrudRepository, SpareEmailCrudRepository spareEmailCrudRepository, UserCrudRepository userCrudRepository, UserInfoCrudRepository userInfoCrudRepository, PublicMethod publicMethod) {
        this.addressCrudRepository = addressCrudRepository;
        this.secondaryEmailCrudRepository = secondaryEmailCrudRepository;
        this.spareEmailCrudRepository = spareEmailCrudRepository;
        this.userCrudRepository = userCrudRepository;
        this.userInfoCrudRepository = userInfoCrudRepository;
        this.publicMethod = publicMethod;
    }


    @Override
    public ResponseResult<AllUserInformationDTO> insertAddress(AllUserInformationDTO allUserInformationDTO) {
        try {

            if (publicMethod.isRightInsertAddress(allUserInformationDTO)) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            AddressBO addressBO = new DozerStruct<AllUserInformationDTO, AddressBO>()
                    .transForm(allUserInformationDTO, AddressBO.class);

            if (addressCrudRepository.insert(addressBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "ADDRESS_INSERT_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.insertAddress");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> insertSpareEmail(AllUserInformationDTO allUserInformationDTO) {
        try {

            if (!publicMethod.isRightInsertSpareEmail(allUserInformationDTO)) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            if (spareEmailCrudRepository.isEmailExist(allUserInformationDTO.getSeEmail()) ||
                    secondaryEmailCrudRepository.isEmailExist(allUserInformationDTO.getSeEmail()) ||
                    userCrudRepository.isEmailExist(allUserInformationDTO.getSeEmail())
            ) {
                return new ResponseResult<>(Code.ALREADY_EXISTS, "EMAIL_ALREADY_EXISTS");
            }

            SpareEmailBO spareEmailBO = new DozerStruct<AllUserInformationDTO, SpareEmailBO>()
                    .transForm(allUserInformationDTO, SpareEmailBO.class);

            if (spareEmailCrudRepository.insert(spareEmailBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "EMAIL_INSERT_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.insertSpareEmail");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> insertSecondaryEmail(AllUserInformationDTO allUserInformationDTO) {
        try {

            if (!publicMethod.isRightInsertSecondaryEmail(allUserInformationDTO)) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            if (spareEmailCrudRepository.isEmailExist(allUserInformationDTO.getReEmail()) ||
                    secondaryEmailCrudRepository.isEmailExist(allUserInformationDTO.getReEmail()) ||
                    userCrudRepository.isEmailExist(allUserInformationDTO.getReEmail())
            ) {
                return new ResponseResult<>(Code.ALREADY_EXISTS, "EMAIL_ALREADY_EXISTS");
            }


            SecondaryEmailBO secondaryEmailBO = new DozerStruct<AllUserInformationDTO, SecondaryEmailBO>()
                    .transForm(allUserInformationDTO, SecondaryEmailBO.class);

            if (secondaryEmailCrudRepository.insert(secondaryEmailBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "EMAIL_INSERT_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.insertSecondaryEmail");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> deleteAddress(AllUserInformationDTO allUserInformationDTO) {
        try {

            if (!publicMethod.isRightDeleteAddress(allUserInformationDTO)) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            AddressBO addressBO = new DozerStruct<AllUserInformationDTO, AddressBO>()
                    .transForm(allUserInformationDTO, AddressBO.class);

            if (addressCrudRepository.delete(addressBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "ADDRESS_DELETE_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.insertSecondaryEmail");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> deleteSpareEmail(AllUserInformationDTO allUserInformationDTO) {
        try {

            if (!publicMethod.isRightDeleteSpareEmail(allUserInformationDTO)) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            SpareEmailBO spareEmailBO = new DozerStruct<AllUserInformationDTO, SpareEmailBO>()
                    .transForm(allUserInformationDTO, SpareEmailBO.class);

            if (spareEmailCrudRepository.delete(spareEmailBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "EMAIL_DELETE_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.deleteSpareEmail");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> deleteSecondaryEmail(AllUserInformationDTO allUserInformationDTO) {
        try {

            if (!publicMethod.isRightDeleteSecondaryEmail(allUserInformationDTO)) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            SecondaryEmailBO secondaryEmailBO = new DozerStruct<AllUserInformationDTO, SecondaryEmailBO>()
                    .transForm(allUserInformationDTO, SecondaryEmailBO.class);

            if (secondaryEmailCrudRepository.delete(secondaryEmailBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "EMAIL_DELETE_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.deleteSecondaryEmail");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> updateAddress(AllUserInformationDTO allUserInformationDTO) {
        try {

            if (!publicMethod.isRightUpdateAddress(allUserInformationDTO)) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            AddressBO addressBO = new DozerStruct<AllUserInformationDTO, AddressBO>()
                    .transForm(allUserInformationDTO, AddressBO.class);

            if (addressCrudRepository.update(addressBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "ADDRESS_UPDATE_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.updateSecondaryEmail");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> updateName(AllUserInformationDTO allUserInformationDTO) {
        try {

            if (!publicMethod.isRightUpdateName(allUserInformationDTO)) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            UserInfoBO userInfoBO = new DozerStruct<AllUserInformationDTO, UserInfoBO>()
                    .transForm(allUserInformationDTO, UserInfoBO.class);

            if (userInfoCrudRepository.updateName(userInfoBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "USERINFO_UPDATE_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.updateName");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> updateGender(AllUserInformationDTO allUserInformationDTO) {
        try {

            if (!publicMethod.isRightUpdateGender(allUserInformationDTO)) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            UserInfoBO userInfoBO = new DozerStruct<AllUserInformationDTO, UserInfoBO>()
                    .transForm(allUserInformationDTO, UserInfoBO.class);

            if (userInfoCrudRepository.updateGender(userInfoBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "USERINFO_UPDATE_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.updateGender");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> updateAvatar(String uId, MultipartFile avatarFile) {
        try {
            // 检查文件类型（仅允许图片）
            if (!avatarFile.getContentType().startsWith("image/")) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "ONLY IMG");
            }

            // 检查文件大小（限制为10MB）
            if (avatarFile.getSize() > 10 * 1024 * 1024) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "TOO LARGE WITH IMG");
            }

            //获取原始文件名
            String originalFileName = avatarFile.getOriginalFilename();

            //形成新的文件名
            String newFileName = UUID.randomUUID().toString() + originalFileName;

            //指定目录名
            String directoryName = uId + "_avatar_directory";

            // 规范化路径
            String filePath = Paths.get(uploadPath, directoryName, newFileName).toString();

            // 创建目录（如果不存在）
            File directory = new File(Paths.get(uploadPath, directoryName).toString());
            if (!directory.exists()) {
                directory.mkdirs();
            }

            //上传图片
            avatarFile.transferTo(new File(filePath));

            UserInfoBO userInfoBO = new UserInfoBO();

            userInfoBO.setUId(uId);

            userInfoBO.setAvatar(directoryName + "/" + newFileName);

            if (userInfoCrudRepository.updateAvatar(userInfoBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "USERINFO_UPDATE_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", uId, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.updateGender");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> updateBirthdate(AllUserInformationDTO allUserInformationDTO) {
        try {

            if (!publicMethod.isRightUpdateBirthdate(allUserInformationDTO)) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            UserInfoBO userInfoBO = new DozerStruct<AllUserInformationDTO, UserInfoBO>()
                    .transForm(allUserInformationDTO, UserInfoBO.class);

            if (userInfoCrudRepository.updateBirthdate(userInfoBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "USERINFO_UPDATE_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.updateBirthdate");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> updatePhone(AllUserInformationDTO allUserInformationDTO) {
        try {
            if (!publicMethod.isRightUpdatePhone(allUserInformationDTO)) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            UserBO userBO = new DozerStruct<AllUserInformationDTO, UserBO>()
                    .transForm(allUserInformationDTO, UserBO.class);

            if (userCrudRepository.updatePhone(userBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "USERINFO_UPDATE_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.updatePhone");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> updatePassword(AllUserInformationDTO allUserInformationDTO) {
        try {
            if (!publicMethod.isRightUpdatePassword(allUserInformationDTO)) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            if (!PasswordRegular.isValidPassword(allUserInformationDTO.getHashPassword())) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            UserBO userBO = new DozerStruct<AllUserInformationDTO, UserBO>()
                    .transForm(allUserInformationDTO, UserBO.class);

            if (userCrudRepository.updatePassword(userBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "USERINFO_UPDATE_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.updatePassword");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> updateSpareEmail(AllUserInformationDTO allUserInformationDTO) {
        try {
            if (publicMethod.isRightUpdateSpareEmail(allUserInformationDTO)) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            if (!EmailRegular.isEmail(allUserInformationDTO.getSeEmail())) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            if (spareEmailCrudRepository.isEmailExist(allUserInformationDTO.getSeEmail()) ||
                    secondaryEmailCrudRepository.isEmailExist(allUserInformationDTO.getSeEmail()) ||
                    userCrudRepository.isEmailExist(allUserInformationDTO.getSeEmail())
            ) {
                return new ResponseResult<>(Code.ALREADY_EXISTS, "EMAIL_ALREADY_EXISTS");
            }

            SpareEmailBO spareEmailBO = new DozerStruct<AllUserInformationDTO, SpareEmailBO>()
                    .transForm(allUserInformationDTO, SpareEmailBO.class);

            if (spareEmailCrudRepository.update(spareEmailBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "USERINFO_UPDATE_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.updatePassword");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> updateSecondaryEmail(AllUserInformationDTO allUserInformationDTO) {
        try {
            if (!publicMethod.isRightUpdateSecondaryEmail(allUserInformationDTO)) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            if (!EmailRegular.isEmail(allUserInformationDTO.getReEmail())) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }

            if (spareEmailCrudRepository.isEmailExist(allUserInformationDTO.getReEmail()) ||
                    secondaryEmailCrudRepository.isEmailExist(allUserInformationDTO.getReEmail()) ||
                    userCrudRepository.isEmailExist(allUserInformationDTO.getReEmail())
            ) {
                return new ResponseResult<>(Code.ALREADY_EXISTS, "EMAIL_ALREADY_EXISTS");
            }

            SecondaryEmailBO secondaryEmailBO = new DozerStruct<AllUserInformationDTO, SecondaryEmailBO>().transForm(allUserInformationDTO, SecondaryEmailBO.class);

            if (secondaryEmailCrudRepository.update(secondaryEmailBO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.OK, "USERINFO_UPDATE_SUCCESS");
            } else {
                return new ResponseResult<>(Code.DATABASE_QUERY_ERROR, "DATABASE_QUERY_ERROR");
            }

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.updateSecondaryEmail");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<UserInformationDTO> findAll(AllUserInformationDTO allUserInformationDTO) {
        try {
            if (allUserInformationDTO.getUId() == null || allUserInformationDTO.getUId().trim().isEmpty()) {
                return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
            }
            if (userCrudRepository.find(new DozerStruct<AllUserInformationDTO, UserBO>().transForm(allUserInformationDTO, UserBO.class)).getCode().equals(Code.NOT_FOUND)) {
                return new ResponseResult<>(Code.NOT_FOUND, "NOT_FOUND");
            }

            UserInfoBO userInfoBO = new DozerStruct<AllUserInformationDTO, UserInfoBO>().transForm(allUserInformationDTO, UserInfoBO.class);

            userInfoBO = userInfoCrudRepository.find(userInfoBO).getData();

            UserInformationDTO userInformationDTO = new DozerStruct<UserInfoBO, UserInformationDTO>().transForm(userInfoBO, UserInformationDTO.class);

            UserBO userBO = new DozerStruct<AllUserInformationDTO, UserBO>().transForm(allUserInformationDTO, UserBO.class);

            userBO = userCrudRepository.find(userBO).getData();

            userInformationDTO.setUPhone(userBO.getUPhone());

            userInformationDTO.setUEmail(userBO.getUEmail());


            SpareEmailBO spareEmailBO = new DozerStruct<AllUserInformationDTO, SpareEmailBO>().transForm(allUserInformationDTO, SpareEmailBO.class);

            List<SpareEmailBO> spareEmailBOList = spareEmailCrudRepository.find(spareEmailBO).getData();

            if ((spareEmailBOList != null && !spareEmailBOList.isEmpty())) {
                List<SpareEmailDTO> spareEmailDTOList = new DozerStruct<SpareEmailBO, SpareEmailDTO>()
                        .transFormList(spareEmailBOList, SpareEmailDTO.class);

                userInformationDTO.setSpareEmailDTOList(spareEmailDTOList);
            }

            SecondaryEmailBO secondaryEmailBO = new DozerStruct<AllUserInformationDTO, SecondaryEmailBO>().transForm(allUserInformationDTO, SecondaryEmailBO.class);

            List<SecondaryEmailBO> secondaryEmailBOList = secondaryEmailCrudRepository.find(secondaryEmailBO).getData();

            if (secondaryEmailBOList != null && !secondaryEmailBOList.isEmpty()) {
                List<SecondaryEmailDTO> secondaryEmailDTOList = new DozerStruct<SecondaryEmailBO, SecondaryEmailDTO>()
                        .transFormList(secondaryEmailBOList, SecondaryEmailDTO.class);

                userInformationDTO.setSecondaryEmailDTOList(secondaryEmailDTOList);
            }

            AddressBO addressBO = new DozerStruct<AllUserInformationDTO, AddressBO>().transForm(allUserInformationDTO, AddressBO.class);

            List<AddressBO> addressBOList = addressCrudRepository.find(addressBO).getData();

            if (addressBOList != null && !addressBOList.isEmpty()) {
                List<AddressDTO> addressDTOList = new DozerStruct<AddressBO, AddressDTO>()
                        .transFormList(addressBOList, AddressDTO.class);

                userInformationDTO.setAddressDTOList(addressDTOList);
            }

            return new ResponseResult<>(Code.OK, "USERINFO_FIND_SUCCESS", userInformationDTO);

            //统一异常处理
        } catch (ApiException e) {
            logger.error("dao error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when querying: {}", allUserInformationDTO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "UserServiceImpl.findAll");
            return new ResponseResult<>();
        }
    }
}
