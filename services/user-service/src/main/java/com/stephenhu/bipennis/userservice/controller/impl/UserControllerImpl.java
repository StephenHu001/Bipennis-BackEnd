package com.stephenhu.bipennis.userservice.controller.impl;

import com.stephenhu.bipennis.model.DTO.userservice.AllUserInformationDTO;
import com.stephenhu.bipennis.model.DTO.userservice.UserInformationDTO;
import com.stephenhu.bipennis.model.VO.userservice.*;
import com.stephenhu.bipennis.userservice.controller.UserController;
import com.stephenhu.bipennis.userservice.controller.publicmethod.JudgeMethod;
import com.stephenhu.bipennis.userservice.service.UserService;
import com.stephenhu.bipennis.util.DozerStruct.DozerStruct;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.code.Code;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ApiException;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ErrorHandler;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Stephen Hu
 */
@RestController
public class UserControllerImpl implements UserController {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);

    private final UserService userService;

    private final JudgeMethod judgeMethod;

    @Autowired
    public UserControllerImpl(UserService userService, JudgeMethod judgeMethod) {
        this.judgeMethod = judgeMethod;
        this.userService = userService;
    }

    @Override
    public ResponseResult<GetAccountPageVO> getUser(PostAccountPageVO postAccountPageVO) {
        if (!judgeMethod.isRightPostAccountPageVO(postAccountPageVO)) {
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }

        try {
            AllUserInformationDTO allUserInformationDTO = new DozerStruct<PostAccountPageVO, AllUserInformationDTO>().transForm(postAccountPageVO, AllUserInformationDTO.class);

            ResponseResult<UserInformationDTO> result = userService.findAll(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            UserInformationDTO userInformationDTO = result.getData();

            GetAccountPageVO getAccountPageVO = new DozerStruct<UserInformationDTO, GetAccountPageVO>().transForm(userInformationDTO, GetAccountPageVO.class);

            return new ResponseResult<>(Code.OK, "OK", getAccountPageVO);

            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when getUser for postAccountPageVO: {}", postAccountPageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.getUser");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<PostInsertAddressPageVO> addAddress(PostInsertAddressPageVO postInsertAddressPageVO) {
        if (!judgeMethod.isRightPostInsertAddressPageVO(postInsertAddressPageVO)) {
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }
        try {

            AllUserInformationDTO allUserInformationDTO = new AllUserInformationDTO();

            allUserInformationDTO.setUId(postInsertAddressPageVO.getUId());

            if (!userService.findAll(allUserInformationDTO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.NOT_FOUND, "User Not Found");
            }

            allUserInformationDTO = new DozerStruct<PostInsertAddressPageVO, AllUserInformationDTO>().transForm(postInsertAddressPageVO, AllUserInformationDTO.class);

            ResponseResult<AllUserInformationDTO> result = userService.insertAddress(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Add Address Success");


            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when addAddress for PostInsertAddressPageVO: {}", postInsertAddressPageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.addAddress");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<PostInsertSpareEmailPageVO> addSpareEmail(PostInsertSpareEmailPageVO postInsertSpareEmailPageVO) {
        if (!judgeMethod.isRightPostInsertSpareEmailPageVO(postInsertSpareEmailPageVO)) {
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }
        try {

            AllUserInformationDTO allUserInformationDTO = new AllUserInformationDTO();

            allUserInformationDTO.setUId(postInsertSpareEmailPageVO.getUId());

            if (!userService.findAll(allUserInformationDTO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.NOT_FOUND, "User Not Found");
            }

            allUserInformationDTO = new DozerStruct<PostInsertSpareEmailPageVO, AllUserInformationDTO>().transForm(postInsertSpareEmailPageVO, AllUserInformationDTO.class);

            ResponseResult<AllUserInformationDTO> result = userService.insertSpareEmail(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Add SpareEmail Success");


            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when addSpareEmail for postInsertSpareEmailPageVO: {}", postInsertSpareEmailPageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.addSpareEmail");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<PostInsertSecondaryEmailPageVO> addSecondaryEmail(PostInsertSecondaryEmailPageVO postInsertSecondaryEmailPageVO) {
        if (!judgeMethod.isRightPostInsertSecondaryEmailPageVO(postInsertSecondaryEmailPageVO)) {
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }
        try {

            AllUserInformationDTO allUserInformationDTO = new AllUserInformationDTO();

            allUserInformationDTO.setUId(postInsertSecondaryEmailPageVO.getUId());

            if (!userService.findAll(allUserInformationDTO).getCode().equals(Code.OK)) {
                return new ResponseResult<>(Code.NOT_FOUND, "User Not Found");
            }

            allUserInformationDTO = new DozerStruct<PostInsertSecondaryEmailPageVO, AllUserInformationDTO>().transForm(postInsertSecondaryEmailPageVO, AllUserInformationDTO.class);

            ResponseResult<AllUserInformationDTO> result = userService.insertSecondaryEmail(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Add SecondaryEmail Success");


            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when addAddress for postAddressPageVO: {}", postInsertSecondaryEmailPageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.addSecondaryEmail");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<PostDeleteAddressPageVO> deleteAddress(PostDeleteAddressPageVO postDeleteAddressPageVO) {
        if (!judgeMethod.isRightPostDeleteAddressPageVO(postDeleteAddressPageVO)) {
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }
        try {

            AllUserInformationDTO allUserInformationDTO = new DozerStruct<PostDeleteAddressPageVO, AllUserInformationDTO>().transForm(postDeleteAddressPageVO, AllUserInformationDTO.class);

            ResponseResult<AllUserInformationDTO> result = userService.deleteAddress(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Delete Address Success");


            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when deleteAddress for postDeleteAddressPageVO: {}", postDeleteAddressPageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.deleteAddress");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<PostDeleteSpareEmailPageVO> deleteSpareEmail(PostDeleteSpareEmailPageVO postDeleteSpareEmailPageVO) {
        if (!judgeMethod.isRightPostDeleteSpareEmailPageVO(postDeleteSpareEmailPageVO)) {
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }
        try {

            AllUserInformationDTO allUserInformationDTO = new DozerStruct<PostDeleteSpareEmailPageVO, AllUserInformationDTO>().transForm(postDeleteSpareEmailPageVO, AllUserInformationDTO.class);

            ResponseResult<AllUserInformationDTO> result = userService.deleteSpareEmail(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Delete SpareEmail Success");


            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when deleteSpareEmail for postDeleteSpareEmailPageVO: {}", postDeleteSpareEmailPageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.deleteSpareEmail");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<PostDeleteSecondaryEmailPageVO> deleteSecondaryEmail(PostDeleteSecondaryEmailPageVO postDeleteSecondaryEmailPageVO) {
        if (!judgeMethod.isRightPostDeleteSecondaryEmailPageVO(postDeleteSecondaryEmailPageVO)) {
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }
        try {

            AllUserInformationDTO allUserInformationDTO = new DozerStruct<PostDeleteSecondaryEmailPageVO, AllUserInformationDTO>().transForm(postDeleteSecondaryEmailPageVO, AllUserInformationDTO.class);

            ResponseResult<AllUserInformationDTO> result = userService.deleteSecondaryEmail(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Delete SecondaryEmail Success");


            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when deleteSecondaryEmail for PostDeleteSecondaryEmailPageVO: {}", postDeleteSecondaryEmailPageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.deleteSecondaryEmail");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<PostUpdateAddressPageVO> updateAddress(PostUpdateAddressPageVO postUpdateAddressPageVO) {
        if (!judgeMethod.isRightPostUpdateAddress(postUpdateAddressPageVO)) {
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");

        }
        try {
            AllUserInformationDTO allUserInformationDTO = new DozerStruct<PostUpdateAddressPageVO, AllUserInformationDTO>().transForm(postUpdateAddressPageVO, AllUserInformationDTO.class);

            ResponseResult<AllUserInformationDTO> result = userService.updateAddress(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Update Address Success");

            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when updateAddress for PostUpdateAddressPagePO: {}", postUpdateAddressPageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.updateAddress");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<PostUpdateNamePageVO> updateName(PostUpdateNamePageVO postUpdateNamePageVO) {
        if (!judgeMethod.isRightPostUpdateName(postUpdateNamePageVO)) {
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }

        try {
            AllUserInformationDTO allUserInformationDTO = new DozerStruct<PostUpdateNamePageVO, AllUserInformationDTO>().transForm(postUpdateNamePageVO, AllUserInformationDTO.class);

            ResponseResult<AllUserInformationDTO> result = userService.updateName(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Update Name Success");

            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when updateName for PostUpdateNamePageVO: {}", postUpdateNamePageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.updateName");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<PostUpdateGenderPageVO> updateGender(PostUpdateGenderPageVO postUpdateGenderPageVO) {
        if (!judgeMethod.isRightPostUpdateGender(postUpdateGenderPageVO)) {
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }
        try {
            AllUserInformationDTO allUserInformationDTO = new DozerStruct<PostUpdateGenderPageVO, AllUserInformationDTO>().transForm(postUpdateGenderPageVO, AllUserInformationDTO.class);

            ResponseResult<AllUserInformationDTO> result = userService.updateGender(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Update Gender Success");

            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when updateGender for PostUpdateGenderPageVO: {}", postUpdateGenderPageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.updateName");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<AllUserInformationDTO> updateAvatar(String uId, MultipartFile avatarFile) {
        if (!judgeMethod.isRightPostUpdateAvatar(uId)) {
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }

        if (avatarFile == null) {
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }

        try {

            ResponseResult<AllUserInformationDTO> result = userService.updateAvatar(uId, avatarFile);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Update Avatar Success");

            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when updateAvatar for uId: {}", uId, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.updateAvatar");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<PostUpdateBirthdatePageVO> updateGender(PostUpdateBirthdatePageVO postUpdateBirthdatePageVO) {
        if(!judgeMethod.isRightPostUpdateBirthdate(postUpdateBirthdatePageVO)){
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }
        try {
            AllUserInformationDTO allUserInformationDTO = new DozerStruct<PostUpdateBirthdatePageVO, AllUserInformationDTO>().transForm(postUpdateBirthdatePageVO, AllUserInformationDTO.class);

            ResponseResult<AllUserInformationDTO> result = userService.updateBirthdate(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Update Birthdate Success");

            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when updateBirthdate for postUpdateBirthdatePageVO: {}", postUpdateBirthdatePageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.updateBirthdate");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<PostUpdatePhonePageVO> updatePhone(PostUpdatePhonePageVO postUpdatePhonePageVO) {
        if (!judgeMethod.isRightPostUpdatePhone(postUpdatePhonePageVO)){
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }
        try {
            AllUserInformationDTO allUserInformationDTO = new DozerStruct<PostUpdatePhonePageVO, AllUserInformationDTO>().transForm(postUpdatePhonePageVO, AllUserInformationDTO.class);

            ResponseResult<AllUserInformationDTO> result = userService.updatePhone(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Update Phone Success");

            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when updatePhone for postUpdatePhonePageVO: {}", postUpdatePhonePageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.updatePhone");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<PostUpdatePasswordPageVO> updatePassword(PostUpdatePasswordPageVO postUpdatePasswordPageVO) {
        if (!judgeMethod.isRightPostUpdatePassword(postUpdatePasswordPageVO)){
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }
        try {
            AllUserInformationDTO allUserInformationDTO = new DozerStruct<PostUpdatePasswordPageVO, AllUserInformationDTO>().transForm(postUpdatePasswordPageVO, AllUserInformationDTO.class);

            ResponseResult<AllUserInformationDTO> result = userService.updatePassword(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Update Password Success");

            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when updatePassword for postUpdatePasswordPageVO: {}", postUpdatePasswordPageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.updatePassword");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<PostUpdateSpareEmailPageVO> updateSpareEmail(PostUpdateSpareEmailPageVO postUpdateSpareEmailPageVO) {
        if (!judgeMethod.isRightPostUpdateSpareEmail(postUpdateSpareEmailPageVO)){
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }
        try {
            AllUserInformationDTO allUserInformationDTO = new DozerStruct<PostUpdateSpareEmailPageVO, AllUserInformationDTO>().transForm(postUpdateSpareEmailPageVO, AllUserInformationDTO.class);

            ResponseResult<AllUserInformationDTO> result = userService.updateSpareEmail(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Update SpareEmail Success");

            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when updateSpareEmail for postUpdatePasswordPageVO: {}", postUpdateSpareEmailPageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.updateSpareEmail");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<PostUpdateSecondaryEmailPageVO> updateSecondaryEmail(PostUpdateSecondaryEmailPageVO postUpdateSecondaryEmailPageVO) {
        if (!judgeMethod.isRightPostUpdateSecondaryEmail(postUpdateSecondaryEmailPageVO)){
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "INVALID_ARGUMENT");
        }

        try {
            AllUserInformationDTO allUserInformationDTO = new DozerStruct<PostUpdateSecondaryEmailPageVO, AllUserInformationDTO>().transForm(postUpdateSecondaryEmailPageVO, AllUserInformationDTO.class);

            ResponseResult<AllUserInformationDTO> result = userService.updateSecondaryEmail(allUserInformationDTO);

            if (!result.getCode().equals(Code.OK)) {
                return new ResponseResult<>(result.getCode(), result.getMsg());
            }

            return new ResponseResult<>(Code.OK, "Update SecondaryEmail Success");

            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when updateSecondaryEmail for postUpdateSecondaryEmailPageVO: {}", postUpdateSecondaryEmailPageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.update");
            return new ResponseResult<>();
        }
    }
}
