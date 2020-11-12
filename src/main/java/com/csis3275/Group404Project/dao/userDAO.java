package com.csis3275.Group404Project.dao;

import com.csis3275.Group404Project.model.USER_404_PROJECT;
import com.csis3275.Group404Project.model.User;
import com.csis3275.Group404Project.model.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class userDAO {

    JdbcTemplate jdbcTemplate;

    private final String SQL_GET_ALL = "select * from USER_404_PROJECT";
    private final String SQL_GET_USER_BY_USERNAME = "select * from USER_404_PROJECT  where USERNAME = ?";
    private final String SQL_DELETE_USER_BY_USERNAME = "delete * from USER_404_PROJECT where USERNAME = ?";
    private final String SQL_UPDATE_USER_BY_USERNAME = "update USER_404_PROJECT set USERNAME = ?, PASSWORD = ?, REPORTSFROM = ?, USERTYPE = ?, TOTAL = ? WHERE USERNAME = ?";
    private final String SQL_UPDATE_PASSWORD_BY_USERNAME = "update USER_404_PROJECT set PASSWORD = ? WHERE USERNAME = ?";
    private final String SQL_INSERT_USER = "insert into USER_404_PROJECT(USERNAME, PASSWORD, REPORTSFROM, USERTYPE, TOTAL) values(?,?,?,?,?)";

    @Autowired
    public userDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean createUser(USER_404_PROJECT user){
        return jdbcTemplate.update(SQL_INSERT_USER, user.getUserName() ,
                user.getPassword(), user.getReportsFrom(),
                user.getUserType(), user.getTotal()) > 0;
    }

    public List<User> getUserByUserName(String userName){

        return jdbcTemplate.query(SQL_GET_USER_BY_USERNAME, new Object[] {userName} , new UserMapper());
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_GET_ALL, new UserMapper());
    }

    public boolean updatePasswordByUserName(String userName, String newPassword){
        return jdbcTemplate.update(SQL_UPDATE_PASSWORD_BY_USERNAME, newPassword, userName) > 0;
    }

}
