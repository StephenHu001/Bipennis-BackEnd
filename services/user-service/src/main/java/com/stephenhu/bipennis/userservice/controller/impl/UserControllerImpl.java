package com.stephenhu.bipennis.userservice.controller.impl;

import com.stephenhu.bipennis.model.DTO.autherservice.LoginDTO;
import com.stephenhu.bipennis.model.DTO.userservice.AllUserInformationDTO;
import com.stephenhu.bipennis.model.DTO.userservice.UserInformationDTO;
import com.stephenhu.bipennis.model.VO.authservice.LoginPageVO;
import com.stephenhu.bipennis.model.VO.userservice.GetAccountPageVO;
import com.stephenhu.bipennis.model.VO.userservice.PostAccountPageVO;
import com.stephenhu.bipennis.userservice.controller.UserController;
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

/**
 * @author Stephen Hu
 */
@RestController
public class UserControllerImpl implements UserController {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);

    private UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseResult<GetAccountPageVO> getUser(PostAccountPageVO postAccountPageVO) {
        if  (postAccountPageVO.getUId() == null || postAccountPageVO.getUId().trim().isEmpty()) {
            ErrorHandler.throwApiException(Code.INVALID_ARGUMENT, "UserControllerImpl.getUser");
        }
        try {
            AllUserInformationDTO allUserInformationDTO = new DozerStruct<PostAccountPageVO, AllUserInformationDTO>().transForm(postAccountPageVO, AllUserInformationDTO.class);

            UserInformationDTO userInformationDTO = userService.findAll(allUserInformationDTO).getData();

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
}
