package com.stephenhu.bipennis.userservice.service;

import com.stephenhu.bipennis.authservice.service.AuthService;
import com.stephenhu.bipennis.model.DTO.autherservice.LoginDTO;
import com.stephenhu.bipennis.model.DTO.autherservice.RegisterDTO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestService {
    @Autowired
    private AuthService authService;
    @Test
    public void Login() {
        LoginDTO loginDTO = new LoginDTO("stephenhu031028@gmail.com", "20021004@Dahuzi");

        ResponseResult<LoginDTO> responseResult = authService.login(loginDTO);

        System.out.println(responseResult);
    }
    @Test
    public void Register() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setLastName("Hu");
        registerDTO.setUEmail("stephenhu031028@gmail.com");
        registerDTO.setUPhone("+86 17387520730");
        registerDTO.setHashPassword("20021004@Dahuzi");
        registerDTO.setBirthdate("2003-10-28");

        System.out.println(authService.register(registerDTO));
    }
}
