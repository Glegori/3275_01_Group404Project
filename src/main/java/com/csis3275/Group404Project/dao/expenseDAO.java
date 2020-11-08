package com.csis3275.Group404Project.dao;


import java.util.List;
import java.util.Map;

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
	private final String SQL_GET_ALL_EXPENSES_BY_USERNAME = "select * from EXPENSE_404_project where USER = ?";
	private final String SQL_GET_EXPENSE_BY_EXPENSETYPE = "SELECT * FROM EXPENSE_404_project WHERE USER = ? AND EXPENSETYPE = ?";
	private final String SQL_INSERT_EXPENSE = "insert into EXPENSE_404_project(expenseName, expenseCost, date, expenseType, expenseStatus, billImage, user) values(?,?,?,?,?,?,?)";
	private final String SQL_GET_TOTAL_COST_BY_CATEGORY = "SELECT EXPENSETYPE, SUM (EXPENSECOST) AS TOTALCOST FROM EXPENSE_404_project GROUP BY EXPENSETYPE";

			

	@Autowired
	public expenseDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

//	public boolean createExpense(Expense expense) {
//		return jdbcTemplate.update(SQL_INSERT_EXPENSE, expense.getExpenseName() ,
//				expense.getExpenseCost(), expense.getDate(),
//				expense.getExpenseType(), expense.getExpenseStatus(),
//				expense.getBillImage(), expense.getUser()) > 0;
//	}
//
	public boolean createExpense(Expense expense, String userName){
		return jdbcTemplate.update(SQL_INSERT_EXPENSE, expense.getExpenseName() ,
				expense.getExpenseCost(), expense.getDate(),
				expense.getExpenseType(), expense.getExpenseStatus(),
				expense.getBillImage(), userName) > 0;
	}

	public List<Expense> getExpensesByUserName(String USER){

		return jdbcTemplate.query(SQL_GET_ALL_EXPENSES_BY_USERNAME, new Object[] {USER} , new ExpenseMapper());
	}
	
	public List<Expense> getExpensesByUserNameAndExpenseType(String USER, String EXPENSETYPE){
		System.out.println("System is searching expense type = "+ EXPENSETYPE + " into the Database");
		return jdbcTemplate.query(SQL_GET_EXPENSE_BY_EXPENSETYPE, new Object[] {USER, EXPENSETYPE} , new ExpenseMapper());
	}

	public List<Expense> getAllExpenses() {
		return jdbcTemplate.query(SQL_GET_ALL, new ExpenseMapper());
	}
	
	public List<Map<String, Object>> getTotalCost() {
		return jdbcTemplate.queryForList(SQL_GET_TOTAL_COST_BY_CATEGORY);
	}
	
	
	
}



