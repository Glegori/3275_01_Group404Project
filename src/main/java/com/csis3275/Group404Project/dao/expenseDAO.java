package com.csis3275.Group404Project.dao;


import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.Group404Project.model.Expense;
import com.csis3275.Group404Project.model.ExpenseMapper;

@Component
public class expenseDAO {

	JdbcTemplate jdbcTemplate;

	private final String SQL_GET_ALL = "select * from EXPENSE_404_project";
	private final String SQL_INSERT_PERSON = "insert into EXPENSE_404_project(expenseName, expenseCost, date, expenseType, expenseStatus, billImage, user) values(?,?,?,?,?,?,?)";

	@Autowired
	public expenseDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public List<ExpenseForm> getAllStudents() {
		return jdbcTemplate.query(SQL_GET_ALL, new ExpenseMapper());
	}
	public boolean createStudent(Expense expense) {
		return jdbcTemplate.update(SQL_INSERT_PERSON, expense.getExpenseName() , 
				expense.getExpenseCost(), expense.getDate(),
				expense.getExpenseType(), expense.getExpenseStatus(), 
				expense.getBillImage() Expense.getUser()) > 0;
	}
}

