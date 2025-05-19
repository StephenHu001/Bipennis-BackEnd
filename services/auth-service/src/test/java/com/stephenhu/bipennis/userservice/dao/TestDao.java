package com.stephenhu.bipennis.userservice.dao;

import com.stephenhu.bipennis.authservice.AuthServiceApplication;
import com.stephenhu.bipennis.authservice.dao.SecondaryEmailCrudRepository;
import com.stephenhu.bipennis.authservice.dao.SpareEmailCrudRepository;
import com.stephenhu.bipennis.authservice.dao.UserCrudRepository;
import com.stephenhu.bipennis.authservice.dao.UserInfoCrudRepository;
import com.stephenhu.bipennis.model.BO.authservice.SecondaryEmailBO;
import com.stephenhu.bipennis.model.BO.authservice.SpareEmailBO;
import com.stephenhu.bipennis.model.BO.authservice.UserBO;
import com.stephenhu.bipennis.model.BO.authservice.UserInfoBO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AuthServiceApplication.class)
public class TestDao {
    @Autowired
    private SpareEmailCrudRepository spareEmailCrudRepository;

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private UserInfoCrudRepository userInfoCrudRepository;

    @Autowired
    private SecondaryEmailCrudRepository secondaryEmailCrudRepository;
    @Test
    public void testPareEmailCrudRepository1() {
        SpareEmailBO spareEmailBO = new SpareEmailBO();
        spareEmailBO.setSeEmail("3474584031@qq.com");
        System.out.println(spareEmailCrudRepository.findByEmail(spareEmailBO));
    }

    @Test
    public void testUserCrudRepository1() {
        UserBO userBO = new UserBO();
        userBO.setUEmail("stephenhu031028@gmail.com");
        System.out.println(userCrudRepository.findByEmail(userBO));
    }

    @Test
    public void testUserCrudRepository2() {
        UserBO userBO = new UserBO();
        userBO.setUPhone("+86 17387520730");
        System.out.println(userCrudRepository.findByPhone(userBO));
    }

    @Test
    public void testUserCrudRepository3() {
        UserBO userBO = new UserBO();
        userBO.setUId("1");
        System.out.println(userCrudRepository.findByUid(userBO));
    }
    @Test
    public void testUserCrudRepository4() {
        UserBO userBO = new UserBO();
        userBO.setUPhone("+86 18406979887");
        userBO.setUEmail("stephen031028@gmail.com");
        userBO.setHashPassword("20021004@Dahuzi");
        System.out.println(userCrudRepository.insertUser(userBO));
    }

    @Test
    public void testUserInfoCrudRepository1() {
        UserInfoBO userBO = new UserInfoBO();
        userBO.setUId("1");
        userBO.setFirstName("");
        userBO.setLastName("Hu");
        userBO.setBirthdate("2003-10-28");
        System.out.println(userInfoCrudRepository.insertUserInfo(userBO));
    }

    @Test
    public void testSecondaryEmailCrudRepository1() {
        SecondaryEmailBO secondaryEmailBO = new SecondaryEmailBO();
        secondaryEmailBO.setReEmail("123456@qq.com");
        System.out.println(secondaryEmailCrudRepository.findByEmail(secondaryEmailBO));
    }

}
