package com.stephenhu.bipennis.authservice.controller;

import com.stephenhu.bipennis.model.VO.authservice.LoginPageVO;
import com.stephenhu.bipennis.model.VO.authservice.RegisterPageVO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Stephen Hu
 */
@RequestMapping("/auth/")
public interface AuthController {
    /**
     * 登录
     * @param loginPageVO 登录信息
     * @return ResponseResult<LoginDTO>
     * */
    @Operation(summary = "Login")
    @PostMapping("login")
    ResponseResult<LoginPageVO> login(@RequestBody LoginPageVO loginPageVO);

    /**
     * 注册
     * @param registerPageVO 注册信息
     * @return ResponseResult<RegisterDTO>
     * */
    @Operation(summary = "Register")
    @PostMapping("register")
    ResponseResult<RegisterPageVO> register(@RequestBody RegisterPageVO registerPageVO);
}
