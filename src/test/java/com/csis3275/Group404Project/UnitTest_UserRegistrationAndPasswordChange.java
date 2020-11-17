package com.csis3275.Group404Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.csis3275.Group404Project.dao.userDAO;
import com.csis3275.Group404Project.model.USER_404_PROJECT;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UnitTest_UserRegistrationAndPasswordChange {

    @Autowired
    userDAO dao;

    USER_404_PROJECT newUser = new USER_404_PROJECT();

    /**    We are using the dao to create a new user in the database, then we use the dao to get the data about the same user
     *    and check if get the correct data. Right now there is no method that we can use other than just reading and altering
     *    the data for the features (registration and password change) so we implemented tests for these.
     *    @author Shubham
     */
    @BeforeAll
    public void addUser(){
        newUser.setUserName("TestUser1");
        newUser.setPassword("12345");
        newUser.setReportsFrom("Francis,Shubham,Alfredo");
        newUser.setUserType("user");
        newUser.setTotal(20.00);
        dao.createUser(newUser);
    }

    @Test
    @Order(1)
    public void testUserName(){
        assertEquals("TestUser1", dao.getUserByUserName("TestUser1").get(0).getUserName());
    }

    @Test
    @Order(2)
    public void testPassword(){
        assertEquals("12345", dao.getUserByUserName("TestUser1").get(0).getPassword());
    }

    @Test
    @Order(3)
    public void testUserType(){
        assertEquals("user", dao.getUserByUserName("TestUser1").get(0).getUserType());
    }

    @Test
    @Order(4)
    public void testUserTotal(){
        assertEquals(20.00, dao.getUserByUserName("TestUser1").get(0).getTotal());
    }

    @Test
    @Order(5)
    public void testUserReportsFrom(){
        assertEquals("Francis,Shubham,Alfredo", dao.getUserByUserName("TestUser1").get(0).getReportsFrom());
    }

    @Test
    @Order(6)
    public void testUserPasswordChange(){
        dao.updatePasswordByUserName(dao.getUserByUserName("TestUser1").get(0).getUserName(), "abcde");
        assertEquals("abcde", dao.getUserByUserName("TestUser1").get(0).getPassword());
    }
}
