package com.csis3275.Group404Project.dao;


import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.Group404Project.model.Expense;
import com.csis3275.Group404Project.model.ExpenseMapper;
import com.csis3275.Group404Project.model.User;
import com.csis3275.Group404Project.model.UserMapper;

@Component
public class expenseDAO {

	JdbcTemplate jdbcTemplate;

	private final String SQL_GET_ALL = "select * from EXPENSE_404_project";
	private final String SQL_GET_ALL_EXPENSES_BY_USERNAME = "select * from EXPENSE_404_project where USER = ?";
	private final String SQL_GET_REPORTS_FROM = "select * from USER_404_project where USERNAME = ?";
	private final String SQL_INSERT_EXPENSE = "insert into EXPENSE_404_project(expenseName, expenseCost, date, expenseType, expenseStatus, billImage, user) values(?,?,?,?,?,?,?)";

	@Autowired
	public expenseDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean createExpense(Expense expense, String userName){
		return jdbcTemplate.update(SQL_INSERT_EXPENSE, expense.getExpenseName() ,
				expense.getExpenseCost(), expense.getDate(),
				expense.getExpenseType(), expense.getExpenseStatus(),
				expense.getBillImage(), userName) > 0;
	}
	public List<Expense> getReportingExpenses(String S_USER){
		List<User> userList = jdbcTemplate.query(SQL_GET_REPORTS_FROM, new Object[] {S_USER}, new UserMapper());
		String[] users = new String[100];
		for(User user:userList) {
			users = user.getReportsFrom().split(",");
		}
		List<Expense> returningExpenses = new ArrayList<>();
		for(String user:users) {
			List<Expense> list = (jdbcTemplate.query(SQL_GET_ALL_EXPENSES_BY_USERNAME, new Object[] {user} , new ExpenseMapper()));
			for(Expense expense:list){
				returningExpenses.add(expense);
			}
		}
		return returningExpenses;
	}

	public List<Expense> getExpensesByUserName(String USER){
		return jdbcTemplate.query(SQL_GET_ALL_EXPENSES_BY_USERNAME, new Object[] {USER} , new ExpenseMapper());
	}

	public List<Expense> getAllExpenses() {
		return jdbcTemplate.query(SQL_GET_ALL, new ExpenseMapper());
	}
}

