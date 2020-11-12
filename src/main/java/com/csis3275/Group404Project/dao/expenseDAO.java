package com.csis3275.Group404Project.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	@Autowired
	userDAO userDAO;

	JdbcTemplate jdbcTemplate;

	private final String SQL_GET_ALL = "select * from EXPENSE_404_project";
	private final String SQL_GET_ALL_EXPENSES_BY_USERNAME = "select * from EXPENSE_404_project where USER = ?";
	private final String SQL_GET_REPORTS_FROM = "select * from USER_404_project where USERNAME = ?";
	private final String SQL_UPDATE_STATUS = "update EXPENSE_404_project SET expenseStatus = ? WHERE id = ?";
	private final String SQL_GET_EXPENSE_BY_EXPENSETYPE = "SELECT * FROM EXPENSE_404_project WHERE USER = ? AND EXPENSETYPE = ?";
	private final String SQL_INSERT_EXPENSE = "insert into EXPENSE_404_project(expenseName, expenseCost, date, expenseType, expenseStatus, billImage, user) values(?,?,?,?,?,?,?)";
	private final String SQL_GET_TOTAL_COST_BY_CATEGORY = "SELECT EXPENSETYPE, SUM (EXPENSECOST) AS TOTALCOST, AVG (EXPENSECOST) AS AVERAGECOST, COUNT (EXPENSECOST) AS TOTALCOUNT FROM EXPENSE_404_project GROUP BY EXPENSETYPE";
	private final String SQL_GET_TOTAL_COST_BY_USER = "SELECT USER, SUM (EXPENSECOST) AS TOTALCOST, AVG (EXPENSECOST) AS AVERAGECOST, COUNT (EXPENSECOST) AS TOTALCOUNT FROM EXPENSE_404_project GROUP BY USER";
	private final String SQL_DELETE_BY_EXPENSE_ID = "delete from EXPENSE_404_project where id = ?";
	private final String SQL_GET_TOTAL_BY_USERNAME = "SELECT SUM(EXPENSECOST) FROM EXPENSE_404_PROJECT WHERE USER = ?";

	@Autowired
	public expenseDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Double getTotalByUserName(String userName){
		return jdbcTemplate.queryForObject(SQL_GET_TOTAL_BY_USERNAME, Double.class, userName);
	}


	public boolean createExpense(Expense expense, String userName){
		userDAO.updateUserTotal(userName, expense.getExpenseCost());
		return jdbcTemplate.update(SQL_INSERT_EXPENSE, expense.getExpenseName() ,
				expense.getExpenseCost(), expense.getDate(),
				expense.getExpenseType(), expense.getExpenseStatus(),
				expense.getBillImage(), userName) > 0;
	}
	public List<Expense> getReportingExpenses(String S_USER){
		List<User> userList = jdbcTemplate.query(SQL_GET_REPORTS_FROM, new Object[] {S_USER}, new UserMapper());
		String[] users = new String[100];
		for(User user:userList) {

			users = user.getReportsFrom().replaceAll("\\s+","").split(",");
		}
		List<Expense> returningExpenses = new ArrayList<>();
		for(String user:users) {
			System.out.println("Query for:" + user);
			List<Expense> list = getExpensesByUserName(user);
			System.out.println(list.size() + " Queries found");
			for(Expense expense:list){
				returningExpenses.add(expense);
			}
		}
		return returningExpenses;
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
	public boolean modifyStatus(Expense expense){
		System.out.println("THE DATA YOUR LOOKING FOR " +expense.getId()+ " " + expense.getExpenseName()+ " "+expense.getExpenseStatus() + " "+expense.getExpenseStatus().getClass().getName());
		return jdbcTemplate.update(SQL_UPDATE_STATUS, expense.getExpenseStatus(), expense.getId()) > 0;
	}

	public List<Map<String, Object>> getTotalCost() {
		return jdbcTemplate.queryForList(SQL_GET_TOTAL_COST_BY_CATEGORY);
	}
	public List<Map<String, Object>> getTotalCostByUser() {
		return jdbcTemplate.queryForList(SQL_GET_TOTAL_COST_BY_USER);
	}

	public boolean deleteExpense(Expense expense){
		System.out.println("The ID of the expense your deleting is: " + expense.getId());
		return jdbcTemplate.update(SQL_DELETE_BY_EXPENSE_ID,  expense.getId()) > 0;
	}



}



