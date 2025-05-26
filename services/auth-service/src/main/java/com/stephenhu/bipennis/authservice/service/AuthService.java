package com.stephenhu.bipennis.authservice.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.stephenhu.bipennis.model.DTO.autherservice.LoginDTO;
import com.stephenhu.bipennis.model.DTO.autherservice.RegisterDTO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Stephen Hu
 */
public interface AuthService {
    /**
     * 登入
     * @param loginDTO 登入信息 账号（可能是主邮箱、可能是备用邮箱、可能是手机号） 密码
     * @return ResponseResult<LoginDTO>
     * */
    ResponseResult<SaTokenInfo> login(LoginDTO loginDTO);

    /**
     * 注册
     * @param registerDTO 注册信息 账号（可能是主邮箱、可能是备用邮箱、可能是手机号） 密码
     * @return ResponseResult<RegisterDTO>
     * */
    ResponseResult<RegisterDTO> register(RegisterDTO registerDTO);

    /**
     * 验证
     *
     * @param request 请求
     * @return Code
     */
    String verifyToken(HttpServletRequest request);

}
