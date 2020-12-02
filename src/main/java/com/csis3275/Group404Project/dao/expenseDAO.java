package com.csis3275.Group404Project.dao;


import java.sql.Date;
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
	private final String SQL_GET_ALL_EXPENSES_BY_ID = "select * from EXPENSE_404_project where id = ?";
	private final String SQL_GET_REPORTS_FROM = "select * from USER_404_project where USERNAME = ?";
	private final String SQL_UPDATE_STATUS = "update EXPENSE_404_project SET expenseStatus = ?, expenseDesc = ? WHERE id = ?";
	private final String SQL_UPDATE_BillImage = "update EXPENSE_404_project SET billImage = ? WHERE id = ?";
	private final String SQL_GET_EXPENSE_BY_EXPENSETYPE = "SELECT * FROM EXPENSE_404_project WHERE USER = ? AND EXPENSETYPE = ?";
	private final String SQL_GET_EXPENSE_BY_EXPENSESTATUS = "SELECT * FROM EXPENSE_404_project WHERE EXPENSESTATUS = ?";
	private final String SQL_GET_EXPENSE_BY_IMAGE = "SELECT * FROM EXPENSE_404_project WHERE USER = ? AND BILLIMAGE = ?";
	private final String SQL_GET_USER_EXPENSE_BY_EXPENSESTATUS = "SELECT * FROM EXPENSE_404_project WHERE USER = ? AND EXPENSESTATUS = ?";
	private final String SQL_INSERT_EXPENSE = "insert into EXPENSE_404_project(expenseName, expenseCost, date, expenseType, expenseStatus, expenseDesc, billImage, user) values(?,?,?,?,?,?,?,?)";
	private final String SQL_GET_TOTAL_COST_BY_CATEGORY = "SELECT EXPENSETYPE, SUM (EXPENSECOST) AS TOTALCOST, AVG (EXPENSECOST) AS AVERAGECOST, COUNT (EXPENSECOST) AS TOTALCOUNT FROM EXPENSE_404_project WHERE EXPENSESTATUS = 'Approved' GROUP BY EXPENSETYPE";
	private final String SQL_GET_TOTAL_COST_BY_USER = "SELECT USER, SUM (EXPENSECOST) AS TOTALCOST, AVG (EXPENSECOST) AS AVERAGECOST, COUNT (EXPENSECOST) AS TOTALCOUNT FROM EXPENSE_404_project WHERE EXPENSESTATUS = 'Approved' GROUP BY USER";
	private final String SQL_DELETE_BY_EXPENSE_ID = "delete from EXPENSE_404_project where id = ?";
	private final String SQL_GET_TOTAL_BY_USERNAME = "SELECT SUM(EXPENSECOST) FROM EXPENSE_404_PROJECT WHERE USER = ?";
	private final String SQL_GET_TOTAL_EXPENSES_SORT_ASC = "SELECT * FROM EXPENSE_404_project WHERE USER = ? order by DATE ASC";
	private final String SQL_GET_TOTAL_EXPENSES_SORT_DESC = "SELECT * FROM EXPENSE_404_project WHERE USER = ? order by DATE DESC";

	//Expenses over Time stuff
	private final String SQL_GET_DATA_BETWEEN_DATES = "SELECT * FROM EXPENSE_404_PROJECT WHERE DATE BETWEEN ? AND ? ORDER BY DATE ASC";

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

	public List<Expense> getExpensesBetweenDates(Date startDate, Date endDate){

		return jdbcTemplate.query(SQL_GET_DATA_BETWEEN_DATES, new Object[] {startDate, endDate}, new ExpenseMapper());

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
				expense.getExpenseType(), expense.getExpenseStatus(), expense.getExpenseDesc(),
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
			List<Expense> list = getExpensesByUserName(user);

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
	 * Gets a list of expenses made by the userID.
	 * @param INT expenseID who is logged in.
	 * @return Returns list of expenses.
	 */
	public List<Expense> getExpensesByID(int expenseID){
		return jdbcTemplate.query(SQL_GET_ALL_EXPENSES_BY_ID, new Object[] {expenseID} , new ExpenseMapper());
	}


	/**
	 *  Gets a list of certain expense type made by the user.
	 * @param USER User who is logged in.
	 * @param EXPENSETYPE Expense type to filter.
	 * @return List of expenses.
	 */
	public List<Expense> getExpensesByUserNameAndExpenseType(String USER, String EXPENSETYPE){

		return jdbcTemplate.query(SQL_GET_EXPENSE_BY_EXPENSETYPE, new Object[] {USER, EXPENSETYPE} , new ExpenseMapper());
	}
	
	/**
	 *  Gets a list of certain expense made by the user logged and the  name of the bill image.
	 * @param USER User who is logged in.
	 * @param PathImage name of the bill image file.
	 * @return List of expenses.
	 */
	public List<Expense> getExpensesByImage(String USER, String PathImage){

		return jdbcTemplate.query(SQL_GET_EXPENSE_BY_IMAGE, new Object[] {USER, PathImage} , new ExpenseMapper());
	}
	
	
	/**
	 *  Gets a list of certain expense status made by the user.
	 * @param USER User who is logged in.
	 * @param EXPENSESTATUS Expense status to filter.
	 * @return List of expenses.
	 */
	public List<Expense> getExpensesByUserAndStatus(String USER, String EXPENSESTATUS){

		return jdbcTemplate.query(SQL_GET_USER_EXPENSE_BY_EXPENSESTATUS, new Object[] {USER, EXPENSESTATUS} , new ExpenseMapper());
	}

	/**
	 * Gets a list of expenses from all users based on status
	 * @param EXPENSESTATUS the status of the expenses you want to fetch
	 * @return
	 */
	public List<Expense> getExpenseByStatus(String EXPENSESTATUS){
		System.out.println(EXPENSESTATUS);
		return jdbcTemplate.query(SQL_GET_EXPENSE_BY_EXPENSESTATUS, new Object[] {EXPENSESTATUS} , new ExpenseMapper());
	}
	/**
	 * Gets a list of certain expense sorted by ASC order
	 * @param USER User who is logged in.
	 * @return List of expenses. 
	 * */
	public List<Expense> getExpensesBySortAsc(String USER){

		return jdbcTemplate.query(SQL_GET_TOTAL_EXPENSES_SORT_ASC, new Object[] {USER} , new ExpenseMapper());
	}
	
	/**
	 * Gets a list of certain expense sorted by DESC order.
	 * @param USER User who is logged in.
	 * @return List of expenses. 
	 * */
	public List<Expense> getExpensesBySortDesc(String USER){

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
		return jdbcTemplate.update(SQL_UPDATE_STATUS, expense.getExpenseStatus(), expense.getExpenseDesc(), expense.getId()) > 0;
	}
	
	/**
	 * Finds and updated the image of an expense.
	 * @param expense The expense being modified.
	 * @return Executes query to update status of the expense.
	 */
	public boolean updateImage(Expense updateExpense){
		return jdbcTemplate.update(SQL_UPDATE_BillImage, updateExpense.getBillImage(), updateExpense.getId()) > 0;
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

		return jdbcTemplate.update(SQL_DELETE_BY_EXPENSE_ID,  expense.getId()) > 0;
	}
}



