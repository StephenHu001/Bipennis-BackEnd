package com.stephenhu.bipennis.userservice.daoTest;

import com.stephenhu.bipennis.model.BO.userservice.SpareEmailBO;
import com.stephenhu.bipennis.userservice.UserServiceApplication;
import com.stephenhu.bipennis.userservice.dao.SpareEmailCrudRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserServiceApplication.class)
public class SpareEmailDaoTest {
    @Autowired
    private SpareEmailCrudRepository spareEmailCrudRepository;
    @Test
    public void insert() {
        SpareEmailBO spareEmailBO = new SpareEmailBO();
        spareEmailBO.setSeEmail("2693614095@qq.com");
        spareEmailBO.setUId("1");
        spareEmailBO.setSeIsPublic("0");
        System.out.println(spareEmailCrudRepository.insert(spareEmailBO));
    }
    @Test
    public void delete() {
        SpareEmailBO spareEmailBO = new SpareEmailBO();
        spareEmailBO.setSeId("2");;
        System.out.println(spareEmailCrudRepository.delete(spareEmailBO));
    }

    @Test
    public void update() {
        SpareEmailBO spareEmailBO = new SpareEmailBO();
        spareEmailBO.setSeId("3");
        spareEmailBO.setSeEmail("2603614095@qq.com");
        spareEmailBO.setUId("1");
        spareEmailBO.setSeIsPublic("0");
        System.out.println(spareEmailCrudRepository.update(spareEmailBO));
    }

    @Test
    public void find() {
        SpareEmailBO spareEmailBO = new SpareEmailBO();
        spareEmailBO.setUId("2");
        System.out.println(spareEmailCrudRepository.find(spareEmailBO));
    }
}
