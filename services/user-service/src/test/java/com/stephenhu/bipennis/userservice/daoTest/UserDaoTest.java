package com.stephenhu.bipennis.userservice.daoTest;

import com.stephenhu.bipennis.model.BO.userservice.UserBO;
import com.stephenhu.bipennis.userservice.dao.UserCrudRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Test
    public void updatePhone() {
        UserBO userBO = new UserBO();
        userBO.setUId("1");
        userBO.setUPhone("+86 17387520730");
        System.out.println(userCrudRepository.updatePhone(userBO));
    }

    @Test
    public void updatePassword() {
        UserBO userBO = new UserBO();
        userBO.setUId("1");
        userBO.setHashPassword("20021004@Dahuzi");
        System.out.println(userCrudRepository.updatePassword(userBO));
    }

    @Test
    public void find() {

        UserBO userBO = new UserBO();

        userBO.setUId("1");

        System.out.println(userCrudRepository.find(userBO));
    }
}
