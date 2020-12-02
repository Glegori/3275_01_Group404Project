package com.csis3275.Group404Project.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates a new user based on the properties given by the DAO.
 *
 *
 */

public class UserMapper implements RowMapper<USER_404_PROJECT> {
	/**
	 * Creates a user based on the result set.
	 */
	public USER_404_PROJECT mapRow(ResultSet resultSet, int i) throws SQLException {
		USER_404_PROJECT user = new USER_404_PROJECT();
		user.setID(resultSet.getInt("ID"));
		user.setUserName(resultSet.getString("USERNAME"));
		user.setPassword(resultSet.getString("PASSWORD"));
		user.setReportsFrom(resultSet.getString("reportsFrom"));
		user.setUserType(resultSet.getString("userType"));
		user.setTotal(resultSet.getDouble("total"));
		return user;
	}
}

