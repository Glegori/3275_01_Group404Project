package com.csis3275.Group404Project.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<USER_404_PROJECT> {

    public USER_404_PROJECT mapRow(ResultSet resultSet, int i) throws SQLException {

        USER_404_PROJECT user = new USER_404_PROJECT();
        user.setUserName(resultSet.getString("USERNAME"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setReportsFROM(resultSet.getString("REPORTSFROM"));
        user.setUserType(resultSet.getString("USERTYPE"));
        user.setTotal(resultSet.getString("TOTAL"));
        return user;
    }
}
