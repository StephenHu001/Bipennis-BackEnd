package com.stephenhu.bipennis.authservice.controller.impl;

import com.stephenhu.bipennis.authservice.controller.AuthController;
import com.stephenhu.bipennis.authservice.controller.publicmethod.JudgeMethod;
import com.stephenhu.bipennis.authservice.dao.impl.SpareEmailCrudRepositoryImpl;
import com.stephenhu.bipennis.authservice.service.AuthService;
import com.stephenhu.bipennis.model.DTO.autherservice.LoginDTO;
import com.stephenhu.bipennis.model.DTO.autherservice.RegisterDTO;
import com.stephenhu.bipennis.model.VO.authservice.LoginPageVO;
import com.stephenhu.bipennis.model.VO.authservice.RegisterPageVO;
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
public final class AuthControllerImpl implements AuthController{
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(SpareEmailCrudRepositoryImpl.class);

    private final AuthService authService;

    private final JudgeMethod judgeMethod;

    @Autowired
    public AuthControllerImpl(AuthService authService,JudgeMethod judgeMethod) {
        this.authService = authService;
        this.judgeMethod = judgeMethod;
    }


    @Override
    public ResponseResult<LoginPageVO> login(LoginPageVO loginPageVO) {
        if (!judgeMethod.isRightLoginPageVO(loginPageVO)){
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "Invalid Login parameter");
        }

        try {
            LoginDTO loginDTO = new DozerStruct<LoginPageVO, LoginDTO>().transForm(loginPageVO, LoginDTO.class);

            ResponseResult<LoginDTO> responseResult = authService.login(loginDTO);

            return new ResponseResult<>(responseResult.getCode(), responseResult.getMsg());

            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when login for LoginPageVO: {}", loginPageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.login");
            return new ResponseResult<>();
        }
    }

    @Override
    public ResponseResult<RegisterPageVO> register(RegisterPageVO registerPageVO) {

        if (!judgeMethod.isRightRegisterPageVO(registerPageVO)) {
            return new ResponseResult<>(Code.INVALID_ARGUMENT, "Invalid Register parameter");
        }

        try {

            RegisterDTO registerDTO = new DozerStruct<RegisterPageVO,RegisterDTO>().transForm(registerPageVO, RegisterDTO.class);

            ResponseResult<RegisterDTO> responseResult = authService.register(registerDTO);

            return new ResponseResult<>(responseResult.getCode(), responseResult.getMsg());

            //统一异常处理
        } catch (ApiException e) {
            logger.error("controller error: Code={}, Message={}, Location={}",
                    e.getErrorCode(), e.getMessage(), e.getLocation());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error when login for LoginPageVO: {}", registerPageVO, e);
            ErrorHandler.throwApiException(Code.INTERNAL, "AuthControllerImpl.register");
            return new ResponseResult<>();
        }
    }
}
