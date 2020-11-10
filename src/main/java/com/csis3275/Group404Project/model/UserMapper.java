package com.csis3275.Group404Project.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet resultSet, int i) throws SQLException {
		private int id;
		private String username;
		private String password;
		private String reportsFrom;
		private String userType;
		private double total;
		
		User user = new User();
		user.setUsername(resultSet.getString("USERNAME"));
		user.setPassword(resultSet.getString("PASSWORD"));
		user.setReportsFrom(resultSet.getString("reportsFrom"));
		user.setUserType(resultSet.getString("userType"));
		user.setUserType(resultSet.getDouble("total"));
		return user;
	}
}