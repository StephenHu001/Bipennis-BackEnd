package com.stephenhu.bipennis.userservice.daoTest;

import com.stephenhu.bipennis.model.BO.userservice.SecondaryEmailBO;
import com.stephenhu.bipennis.userservice.dao.SecondaryEmailCrudRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SecondaryEmailDaoTest {
    @Autowired
    private SecondaryEmailCrudRepository secondaryEmailCrudRepository;
    @Test
    public void insert() {
        SecondaryEmailBO secondaryEmailBO = new SecondaryEmailBO();

        secondaryEmailBO.setUId("1");
        secondaryEmailBO.setReIsPublic("1");
        secondaryEmailBO.setReEmail("test347459@qq.com");

        System.out.println(secondaryEmailCrudRepository.insert(secondaryEmailBO));

    }

    @Test
    public void delete() {
        SecondaryEmailBO secondaryEmailBO = new SecondaryEmailBO();

        secondaryEmailBO.setReId("4");

        System.out.println(secondaryEmailCrudRepository.delete(secondaryEmailBO));

    }

    @Test
    public void update() {
        SecondaryEmailBO secondaryEmailBO = new SecondaryEmailBO();
        secondaryEmailBO.setReId("4");
        secondaryEmailBO.setUId("1");
        secondaryEmailBO.setReIsPublic("0");
        secondaryEmailBO.setReEmail("test347469@qq.com");
        System.out.println(secondaryEmailCrudRepository.update(secondaryEmailBO));
    }

    @Test
    public void find() {
        SecondaryEmailBO secondaryEmailBO = new SecondaryEmailBO();
        secondaryEmailBO.setUId("1");
        System.out.println(secondaryEmailCrudRepository.find(secondaryEmailBO));
    }
}
