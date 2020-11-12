package com.csis3275.Group404Project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.csis3275.Group404Project.dao.userDAO;
import com.csis3275.Group404Project.model.USER_404_PROJECT;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;


@SpringBootTest
public class UnitTest_UserRegistrationAndPasswordChange {

    @Autowired
    userDAO dao;


    USER_404_PROJECT newUser = new USER_404_PROJECT();

/**    We are using the dao to create a new user in the database, then we use the dao to get the data about the same user
*    and check if get the correct data. Right now there is no method that we can use other than just reading and altering
*    the data for the features (registration and password change) so we implemented tests for these.
*    @author Shubham
*/
    @BeforeEach
    public void addUser(){
        newUser.setUserName("TestUser1");
        newUser.setPassword("12345");
        newUser.setReportsFrom("Francis,Shubham,Alfredo");
        newUser.setUserType("user");
        newUser.setTotal(20.00);
        dao.createUser(newUser);

    }

    @Test
    public void testUserName(){
        assertEquals("TestUser1", dao.getUserByUserName("TestUser1").get(0).getUsername());
    }

    @Test
    public void testPassword(){
        assertEquals("12345", dao.getUserByUserName("TestUser1").get(0).getPassword());
    }

    @Test
    public void testUserType(){
        assertEquals("user", dao.getUserByUserName("TestUser1").get(0).getUserType());
    }

    @Test
    public void testUserTotal(){
        assertEquals(20.00, dao.getUserByUserName("TestUser1").get(0).getTotal());
    }

    @Test
    public void testUserReportsFrom(){
        assertEquals("Francis,Shubham,Alfredo", dao.getUserByUserName("TestUser1").get(0).getReportsFrom());
    }

    @Test
    public void testUserPasswordChange(){
       dao.updatePasswordByUserName(dao.getUserByUserName("TestUser1").get(0).getUsername(), "abcde");
       assertEquals(dao.getUserByUserName("TestUser1").get(0).getPassword(), "abcde");
    }

}
