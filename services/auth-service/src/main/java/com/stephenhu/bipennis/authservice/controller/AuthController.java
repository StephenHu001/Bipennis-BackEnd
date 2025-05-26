package com.stephenhu.bipennis.authservice.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.stephenhu.bipennis.model.VO.authservice.LoginPageVO;
import com.stephenhu.bipennis.model.VO.authservice.RegisterPageVO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
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
    ResponseResult<SaTokenInfo> login(@RequestBody LoginPageVO loginPageVO);

    /**
     * 注册
     * @param registerPageVO 注册信息
     * @return ResponseResult<RegisterDTO>
     * */
    @Operation(summary = "Register")
    @PostMapping("register")
    ResponseResult<RegisterPageVO> register(@RequestBody RegisterPageVO registerPageVO);

    /**
     * 验证token
     * @param request 请求
     * @return ResponseResult<String>
     * */
    @Operation(summary = "Verify Token")
    @PostMapping("verifyToken")
    ResponseResult<String> verifyToken(HttpServletRequest request);
}
