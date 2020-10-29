package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ExpenseMapper implements RowMapper<Expense> {

	public expense mapRow(ResultSet resultSet, int i) throws SQLException {

		Expense expense = new Expense();
		expense.setExpenseName(resultSet.getString("expenseName"));
		expense.setExpenseCost(resultSet.getDouble("expenseCost"));
		expense.setDate(resultSet.getString("date"));
		expense.setexpenseType(resultSet.getString("expenseType"));
		expense.setexpenseStatus(resultSet.getString("expenseStatus"));
		expense.setBillImage(resultSet.getString("billImage"));
		expense.setUser(resultSet.getString("user"));
		return expense;
	}
}