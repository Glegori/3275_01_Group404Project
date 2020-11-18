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
import com.csis3275.Group404Project.model.USER_404_PROJECT;
import com.csis3275.Group404Project.model.UserMapper;



/**
 *
 * Executes various SQL queries to be used in the boot controller.
 *
 */

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
	private final String SQL_GET_EXPENSE_BY_EXPENSESTATUS = "SELECT * FROM EXPENSE_404_project WHERE USER = ? AND EXPENSESTATUS = ?";
	private final String SQL_INSERT_EXPENSE = "insert into EXPENSE_404_project(expenseName, expenseCost, date, expenseType, expenseStatus, billImage, user) values(?,?,?,?,?,?,?)";
	private final String SQL_GET_TOTAL_COST_BY_CATEGORY = "SELECT EXPENSETYPE, SUM (EXPENSECOST) AS TOTALCOST, AVG (EXPENSECOST) AS AVERAGECOST, COUNT (EXPENSECOST) AS TOTALCOUNT FROM EXPENSE_404_project WHERE EXPENSESTATUS = 'Approved' GROUP BY EXPENSETYPE";
	private final String SQL_GET_TOTAL_COST_BY_USER = "SELECT USER, SUM (EXPENSECOST) AS TOTALCOST, AVG (EXPENSECOST) AS AVERAGECOST, COUNT (EXPENSECOST) AS TOTALCOUNT FROM EXPENSE_404_project WHERE EXPENSESTATUS = 'Approved' GROUP BY USER";
	private final String SQL_DELETE_BY_EXPENSE_ID = "delete from EXPENSE_404_project where id = ?";
	private final String SQL_GET_TOTAL_BY_USERNAME = "SELECT SUM(EXPENSECOST) FROM EXPENSE_404_PROJECT WHERE USER = ?";
	private final String SQL_GET_TOTAL_EXPENSES_SORT_ASC = "SELECT * FROM EXPENSE_404_project WHERE USER = ? order by DATE ASC";
	private final String SQL_GET_TOTAL_EXPENSES_SORT_DESC = "SELECT * FROM EXPENSE_404_project WHERE USER = ? order by DATE DESC";

	/**
	 * Gets the jdbc connection to h2.
	 *
	 */
	@Autowired
	public expenseDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Double getTotalByUserName(String userName){
		return jdbcTemplate.queryForObject(SQL_GET_TOTAL_BY_USERNAME, Double.class, userName);
	}

	/**
	 * Adds an entry to the list of expenses table.
	 * @param expense The expense to be added.
	 * @param userName The user who is entering the expense.
	 * @return The query to create an expense.
	 */
	public boolean createExpense(Expense expense, String userName){
		userDAO.updateUserTotal(userName, expense.getExpenseCost());
		return jdbcTemplate.update(SQL_INSERT_EXPENSE, expense.getExpenseName() ,
				expense.getExpenseCost(), expense.getDate(),
				expense.getExpenseType(), expense.getExpenseStatus(),
				expense.getBillImage(), userName) > 0;
	}

	/**
	 * Grabs the user and who they report from. This brings back comma seperate values into a list that get expenses based on the usernames
	 * in the list.
	 * @param S_USER The user to gets reports from.
	 * @return Redirects to the returning expenses page.
	 */
	public List<Expense> getReportingExpenses(String S_USER){
		List<USER_404_PROJECT> userList = jdbcTemplate.query(SQL_GET_REPORTS_FROM, new Object[] {S_USER}, new UserMapper());
		String[] users = new String[100];
		for(USER_404_PROJECT user:userList) {

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

	/**
	 * Gets a list of expenses made by the user.
	 * @param USER User who is logged in.
	 * @return Returns list of expenses.
	 */
	public List<Expense> getExpensesByUserName(String USER){
		return jdbcTemplate.query(SQL_GET_ALL_EXPENSES_BY_USERNAME, new Object[] {USER} , new ExpenseMapper());
	}


	/**
	 *  Gets a list of certain expense type made by the user.
	 * @param USER User who is logged in.
	 * @param EXPENSETYPE Expense type to filter.
	 * @return List of expenses.
	 */
	public List<Expense> getExpensesByUserNameAndExpenseType(String USER, String EXPENSETYPE){
		System.out.println("System is searching expense type = "+ EXPENSETYPE + " into the Database");
		return jdbcTemplate.query(SQL_GET_EXPENSE_BY_EXPENSETYPE, new Object[] {USER, EXPENSETYPE} , new ExpenseMapper());
	}
	
	
	/**
	 *  Gets a list of certain expense status made by the user.
	 * @param USER User who is logged in.
	 * @param EXPENSESTATUS Expense status to filter.
	 * @return List of expenses.
	 */
	public List<Expense> getExpensesByUserAndStatus(String USER, String EXPENSESTATUS){
		System.out.println("System is searching expense status = "+ EXPENSESTATUS + " into the Database");
		return jdbcTemplate.query(SQL_GET_EXPENSE_BY_EXPENSESTATUS, new Object[] {USER, EXPENSESTATUS} , new ExpenseMapper());
	}
	
	/*
	 * Gets a list of certain expense type made by the user.
	 * @param USER User who is logged in.
	 * @param SORTEXPENSE it is the sort type asc or desc.
	 * @return List of expenses. 
	 * */
	public List<Expense> getExpensesBySortAsc(String USER){
		System.out.println("System is sorting expense type = ASC into the Database");
		return jdbcTemplate.query(SQL_GET_TOTAL_EXPENSES_SORT_ASC, new Object[] {USER} , new ExpenseMapper());
	}
	
	public List<Expense> getExpensesBySortDesc(String USER){
		System.out.println("System is sorting expense type = DESC into the Database");
		return jdbcTemplate.query(SQL_GET_TOTAL_EXPENSES_SORT_DESC, new Object[] {USER} , new ExpenseMapper());
	}
	

	/**
	 * Gets a list of everyone single expense in the database.
	 * @return List of expenses.
	 */
	public List<Expense> getAllExpenses() {
		return jdbcTemplate.query(SQL_GET_ALL, new ExpenseMapper());
	}

	/**
	 * Finds and updated the status of an expense.
	 * @param expense The expense being modified.
	 * @return Executes query to update status of the expense.
	 */
	public boolean modifyStatus(Expense expense){
		System.out.println("THE DATA YOUR LOOKING FOR " + expense.getId() + " " + expense.getExpenseName()+ " " + expense.getExpenseStatus() + " " + expense.getExpenseStatus());
		return jdbcTemplate.update(SQL_UPDATE_STATUS, expense.getExpenseStatus(), expense.getId()) > 0;
	}


	/**
	 * Gets various statistics from the database based on category.
	 * @return Returns list of expenses statistics.
	 */
	public List<Map<String, Object>> getTotalCost() {
		return jdbcTemplate.queryForList(SQL_GET_TOTAL_COST_BY_CATEGORY);
	}

	/**
	 * Gets various statistics from the database based on user.
	 * @return Returns list of expense statistics.
	 */

	public List<Map<String, Object>> getTotalCostByUser() {
		return jdbcTemplate.queryForList(SQL_GET_TOTAL_COST_BY_USER);
	}

	/**
	 * Deletes an expense according to the given expense object, using the primary key ID.
	 * @return Returns list of expense statistics.
	 */
	public boolean deleteExpense(Expense expense){
		System.out.println("The ID of the expense your deleting is: " + expense.getId());
		return jdbcTemplate.update(SQL_DELETE_BY_EXPENSE_ID,  expense.getId()) > 0;
	}



}



