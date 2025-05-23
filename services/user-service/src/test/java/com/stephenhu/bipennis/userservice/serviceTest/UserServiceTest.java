package com.stephenhu.bipennis.userservice.serviceTest;

import com.stephenhu.bipennis.model.DTO.userservice.AllUserInformationDTO;
import com.stephenhu.bipennis.userservice.UserServiceApplication;
import com.stephenhu.bipennis.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserServiceApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void find() {

        AllUserInformationDTO allUserInformationDTO = new AllUserInformationDTO();

        allUserInformationDTO.setUId("1");

        System.out.println(userService.findAll(allUserInformationDTO));

    }
}
