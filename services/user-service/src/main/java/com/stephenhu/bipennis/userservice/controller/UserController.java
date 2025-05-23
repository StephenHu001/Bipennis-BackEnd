package com.stephenhu.bipennis.userservice.controller;

import com.stephenhu.bipennis.model.VO.authservice.LoginPageVO;
import com.stephenhu.bipennis.model.VO.userservice.GetAccountPageVO;
import com.stephenhu.bipennis.model.VO.userservice.PostAccountPageVO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Stephen Hu
 */
@RequestMapping("/user/")
public interface UserController {
    /**
     * 获取
     * @param postAccountPageVO 登录信息
     * @return ResponseResult<LoginDTO>
     * */
    @Operation(summary = "getUser")
    @PostMapping("getUser")
    ResponseResult<GetAccountPageVO> getUser(@RequestBody PostAccountPageVO postAccountPageVO);
}
