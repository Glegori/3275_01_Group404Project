package com.csis3275.Group404Project.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;

import org.springframework.jdbc.core.RowMapper;

public class ExpenseMapper implements RowMapper<Expense> {

	public Expense mapRow(ResultSet resultSet, int i) throws SQLException {

		Expense expense = new Expense();
		expense.setExpenseName(resultSet.getString("expenseName"));
		expense.setExpenseCost(resultSet.getDouble("expenseCost"));
		expense.setDate(resultSet.getString("date"));
		expense.setExpenseType(resultSet.getString("expenseType"));
		expense.setExpenseStatus(resultSet.getString("expenseStatus"));
		expense.setBillImage(resultSet.getString("billImage"));
		expense.setUser(resultSet.getString("user"));
		return expense;
	}
}