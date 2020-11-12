package com.csis3275.Group404Project.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet resultSet, int i) throws SQLException {
		User user = new User();
		user.setUsername(resultSet.getString("USERNAME"));
		user.setPassword(resultSet.getString("PASSWORD"));
		user.setReportsFrom(resultSet.getString("reportsFrom"));
		user.setUserType(resultSet.getString("userType"));
		user.setTotal(resultSet.getDouble("total"));
		return user;
	}
}

