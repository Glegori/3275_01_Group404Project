package com.csis3275.Group404Project.controller;

import javax.servlet.http.HttpSession;


import com.csis3275.Group404Project.dao.userDAO;
import com.csis3275.Group404Project.model.USER_404_PROJECT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.model.Expense;



import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;

/**
 *
 * The boot controller is the controller that loads models into the jsps as well as handle
 * all routes when a user is clicking around a page.
 *
 */

@Controller
public class BootController {

//	@RequestMapping("/")
//	public String index() {
//		return "Greetings from Spring Boot!";
//	}
//
	@Autowired
	expenseDAO expenseDao;

	@Autowired
	userDAO userDAO;

	/**
	 * Saves our Expense class to be used in models.
	 * @return
	 */
	
	@ModelAttribute("Expense")
	public Expense setupAddForm() {
		return new Expense();
	}

	/**
	 * Saves our User class to be used in models.
	 * @return
	 */
	@ModelAttribute("User")
	public USER_404_PROJECT setupUserForm(){ return new USER_404_PROJECT();}

	/**
	 * Saves our Expense class to be used in models.
	 * @return
	 */
	@ModelAttribute("modExpense")
	public Expense setupModifyForm() {
		return new Expense();
	}
	/**
	 *
	 * Shows the login screen.
	 * @param model This holds all the expenses from the database.
	 * @return Returns to the login screen.
	 */
	@GetMapping("/loginScreen")
	public String showLoginScreen(HttpSession session, Model model) {
		
		List<Expense> expense = expenseDao.getAllExpenses();

		model.addAttribute("loginScreen", expense);

	    return "loginScreen";
	}

	/**
	 * This grabs the user who logged in to be used when loading the expenses.
	 *
	 * @param model Holds a list of all the expenses by the user who logged in.
	 * @return Redirects to the home page.
	 */
	@GetMapping("/homePage")
	public String showHomePage(HttpSession session, Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		List<Expense> expenses = expenseDao.getExpensesByUserName(currentPrincipalName);
		
		model.addAttribute("currentUserExpenses", expenses);

		return "homePage";
	}
	/**
	 * This grabs the user who logged in to be used when loading the expenses.
	 *
	 * @param model Holds a list of all the expenses from the users who report to
	 * who logged in.
	 * @return Redirects to the decision page.
	 */
	@GetMapping("/decisionPage")
	public String showDecisionPage(HttpSession session, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		List<Expense> expenses = expenseDao.getReportingExpenses(currentPrincipalName);
		model.addAttribute("reportingUserExpenses", expenses);

		return "decisionPage";
	}
	/**
	 * Page that shows various reports for expenses and users.
	 * @param model Holds expense statistics to be shown in graphs as well as table
	 * by the expense category.
	 * @return Redirects to the reports page.
	 */
	@GetMapping("/reports")
	public String barGraphCategory(Model model) {
		List<Map<String, Object>> expenseCategory = expenseDao.getTotalCost();
		List<Map<String, Object>> expenseUser = expenseDao.getTotalCostByUser();
			model.addAttribute("expenseCategory", expenseCategory);
		model.addAttribute("expenseUser", expenseUser);
		return "reports";
	}

	//checkLogin
	//	@PostMapping("/loginScreen")
//		public String loginScreen(@ModelAttribute("loginScreen") user checkUser, Model model)	{
			
			//do something
			 
	//		return "submitExpense";
	//	}
	//showForm

	/**
	 *
	 * Page allows you to add an expense to the database.
	 * @return Redirects to the page to submit expenses.
	 */
	@GetMapping("/submitExpense")
	public String submitExpense(HttpSession session, Model model) {
		
		//List<expense> expense = expenseDao.getAllExpenses();

		//model.addAttribute("loginScreen", expense);

	    return "submitExpense";
	}

	/**
	 *
	 *  Shows the expenses.
	 * @return Redirects to the page to show expenses.
	 */
	//ShowExpenses
	@GetMapping("/showExpenses")
	public String showExpenses(HttpSession session, Model model) {
		//List<expense> expense = expenseDao.getAllExpenses();
		//model.addAttribute("expenses", expense);
	    return "showExpense";
	}
//CreateExpenses
//	@PostMapping("/submitExpense")
//	public String createExpense(@ModelAttribute("expense") Expense createExpense, Model model)	{
//
//		 return "showExpense";
//	}


	/**
	 *
	 * Grabs all the data that was entered into the form and create a new expense. This expense
	 * is then added to the database.
	 * @return Redirects back to homepage.
	 */
	@PostMapping("/createExpense")
	public String createExpense(@ModelAttribute("Expense") Expense createExpense, Model model){
//		List<Expense> expenses = expenseDao.getAllExpenses();
//		model.addAttribute("")
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		expenseDao.createExpense(createExpense, currentPrincipalName);
		System.out.println("I am in create Expense and my userName is " + currentPrincipalName);
		List<Expense> expenses = expenseDao.getExpensesByUserName(currentPrincipalName);
        model.addAttribute("currentUserExpenses", expenses);
		return "homePage";
	}

	/**
	 * Grabs the expense that needs to be modified and edits the status in the database based
	 * on what was inputed.
	 *
	 * @return Redirects back to the decision page.
	 */
	@PostMapping("/modifyStatus")
	public String modifyStatus(@ModelAttribute("modExpense")Expense modifyExpense, Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		expenseDao.modifyStatus(modifyExpense);

		List<Expense> expenses = expenseDao.getReportingExpenses(currentPrincipalName);
		model.addAttribute("reportingUserExpenses", expenses);
		return "decisionPage";
	}
//	@GetMapping("/createUser")
//	public String createNewUser(HttpSession session, Model model){
//		return "createUser";
//	}

	/**
	 * Redirects user to be able to either create a new user if they're an admin
	 * or show an error otherwise.
	 * @return If the user return is an admin, redirect to create a user or forbidden if not.
	 */
	@GetMapping("/createUser")
	public String createUser(){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		USER_404_PROJECT user = userDAO.getUserByUserName(currentPrincipalName).get(0);

		if(user.getUserType().equals("admin")){
			return ("createUser");
		} else {

			return("forbidden");

		}

	}
	/**
	 * Error page if the user entered a page they weren't suppose to see.
	 * @return Redirects to the forbidden page.
	 */
	@GetMapping("/forbidden")
	public String forbidden(){
		return ("forbidden");
	}

	/**
	 * Creates a new user to be added to the database.
	 *
	 * @return Redirects to the create a new user page.
	 */
	@PostMapping("/submitUser")
	public String createNewUser(@ModelAttribute("User") USER_404_PROJECT createUser, Model model){

		userDAO.createUser(createUser);
		List<USER_404_PROJECT> users = userDAO.getAllUsers();
		model.addAttribute("Users", users);

		return "createUser";
	}

	/**
	 *  Page that lets the the user change the password.
	 * @return Redirects to change password page.
	 */
	@GetMapping("/changePassword")
	public String changePasswordScreen(){
		return ("changePassword");
	}

	/**
	 * Checks to see if the old password matches the one inputed. If so, update it in the database,
	 * otherwise throw an error.
	 * @param oldPassword Password to be updated.
	 * @param newPassword Password that replaces old pasword.
	 * @return The change password page.
	 */
	@PostMapping("/submitPassword")
	public String submitPassword(@RequestParam String oldPassword, @RequestParam String newPassword, Model model){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		USER_404_PROJECT currentUser = userDAO.getUserByUserName(currentPrincipalName).get(0);

		if(oldPassword.equals(currentUser.getPassword())) {
			userDAO.updatePasswordByUserName(currentPrincipalName, newPassword);
			model.addAttribute("message", "Password for " + currentPrincipalName + " has been successfully updated!");
		} else {
			model.addAttribute("error", "Cannot update password for the current user. " +
					"Please try again, if the issue persists please contact an admin.");

		}

		return ("changePassword");
	}

	/**
	 *
	 * Shows a filtered list of expenses based on the category selected.
	 * @return Redirects to home page.
	 */
	@GetMapping("/filterExpense")
	public String filterExpense(@RequestParam(required = true) String expenseType, Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		System.out.println("I am inside filterExpense Method");
		System.out.println("Value of expenseType is = "+expenseType);
		
		//BRING LIST
		List<Expense> expenses = expenseDao.getExpensesByUserNameAndExpenseType(currentPrincipalName, expenseType);
		System.out.println("Database returns a total of: " + expenses.size() + " rows");
		 
		//MODEL
		model.addAttribute("currentUserExpenses", expenses);
		
		return "homePage";
	}
	
	/**
	 *
	 * Shows a filtered list of expenses based on the sorting method selected.
	 * @return Redirects to home page.
	 */
	@GetMapping("/sortExpenseDate")
	public String sortExpenseDate(@RequestParam(required = true) String sortExpense, Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		System.out.println("I am inside sortexpense Method");
		System.out.println("Value of sortExpense is = "+sortExpense);
		
		
		//BRING LIST
		if(sortExpense.equals("ASC"))
		{
			List<Expense> expenses = expenseDao.getExpensesBySortAsc(currentPrincipalName);
			System.out.println("Database returns a total of: " + expenses.size() + " rows order by date asc");
			//MODEL
			model.addAttribute("currentUserExpenses", expenses);
		}
		else if(sortExpense.equals("DESC"))
		{
			List<Expense> expenses = expenseDao.getExpensesBySortDesc(currentPrincipalName);
			System.out.println("Database returns a total of: " + expenses.size() + " rows order by date desc");
			//MODEL
			model.addAttribute("currentUserExpenses", expenses);
		}
		
		return "homePage";
	}
	
	/**
	 *
	 * Shows a filtered list of expenses based on the expense status selected.
	 * @return Redirects to home page.
	 */
	@GetMapping("/filterExpenseByStatus")
	public String filterExpenseByStatus(@RequestParam(required = true) String expenseStatus, Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		System.out.println("I am inside filterExpense by status Method");
		System.out.println("Value of expenseType status is = "+expenseStatus);
		
		//BRING LIST
		List<Expense> expenses = expenseDao.getExpensesByUserAndStatus(currentPrincipalName, expenseStatus);
		System.out.println("Database returns a total of: " + expenses.size() + " rows expenses by expenses status");
		 
		//MODEL
		model.addAttribute("currentUserExpenses", expenses);
		
		return "homePage";
	}
	
	

}

