package com.stephenhu.bipennis.userservice.daoTest;

import com.stephenhu.bipennis.model.BO.userservice.AddressBO;
import com.stephenhu.bipennis.userservice.UserServiceApplication;
import com.stephenhu.bipennis.userservice.dao.AddressCrudRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserServiceApplication.class)
public class AddressDaoTest {
    @Autowired
    private AddressCrudRepository addressRepository;

    @Test
    public void insert() {
        AddressBO addressBO = new AddressBO();
        addressBO.setUId("1");
        addressBO.setAIsPublic("0");
        addressBO.setCity("Berlin");
        addressBO.setLatitude("52.5185");
        addressBO.setLongitude("13.4046");
        addressBO.setAddressLabel("Test");

        System.out.println(addressRepository.insert(addressBO));
    }

    @Test
    public void delete() {
        AddressBO addressBO = new AddressBO();
        addressBO.setAId("2");

        System.out.println(addressRepository.delete(addressBO));
    }

    @Test
    public void update() {
        AddressBO addressBO = new AddressBO();
        addressBO.setAId("3");
        addressBO.setUId("10");
        addressBO.setAIsPublic("0");
        addressBO.setCity("China");
        addressBO.setLatitude("52.5185");
        addressBO.setLongitude("13.4046");
        addressBO.setAddressLabel("Test");

        System.out.println(addressRepository.update(addressBO));
    }

    @Test
    public void find() {
        AddressBO addressBO = new AddressBO();
        addressBO.setUId("1");

        System.out.println(addressRepository.find(addressBO));
    }

}
