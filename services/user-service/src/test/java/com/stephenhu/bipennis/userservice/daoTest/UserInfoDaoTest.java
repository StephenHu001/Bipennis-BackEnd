package com.stephenhu.bipennis.userservice.daoTest;

import com.stephenhu.bipennis.model.BO.userservice.UserInfoBO;
import com.stephenhu.bipennis.userservice.UserServiceApplication;
import com.stephenhu.bipennis.userservice.dao.UserInfoCrudRepository;
import com.stephenhu.bipennis.userservice.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserServiceApplication.class)
public class UserInfoDaoTest {
    @Autowired
    private UserInfoCrudRepository userInfoCrudRepository;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void updateName() {
        UserInfoBO userInfoBO = new UserInfoBO();

        userInfoBO.setUId("1");

        userInfoBO.setFirstName("Hu");

        userInfoBO.setLastName("Stephen");

        System.out.println(userInfoCrudRepository.updateName(userInfoBO));
    }

    @Test
    public void updateGender() {
        UserInfoBO userInfoBO = new UserInfoBO();

        userInfoBO.setUId("1");

        userInfoBO.setGender("直升飞机");

        userInfoBO.setGenderIsPublic("1");

        System.out.println(userInfoCrudRepository.updateGender(userInfoBO));
    }

    @Test
    public void updateAvatar() {
        UserInfoBO userInfoBO = new UserInfoBO();

        userInfoBO.setUId("1");

        userInfoBO.setAvatar("default.png");

        System.out.println(userInfoCrudRepository.updateAvatar(userInfoBO));
    }

    @Test
    public void updateBirthdate() {
        UserInfoBO userInfoBO = new UserInfoBO();

        userInfoBO.setUId("1");

        userInfoBO.setBirthdate("2003-10-28");

        userInfoBO.setBirthdateIsPublic("0");

        System.out.println(userInfoCrudRepository.updateBirthdate(userInfoBO));
    }

    @Test
    public void find() {
        UserInfoBO userInfoBO = new UserInfoBO();
        userInfoBO.setUId("2");
        System.out.println(userInfoCrudRepository.find(userInfoBO));
    }
}
