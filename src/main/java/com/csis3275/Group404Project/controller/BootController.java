package com.csis3275.Group404Project.controller;

import javax.servlet.http.HttpSession;


import com.csis3275.Group404Project.dao.userDAO;
import com.csis3275.Group404Project.model.USER_404_PROJECT;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import antlr.StringUtils;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.model.Expense;



import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
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
	public String reports(Model model) {
		List<Map<String, Object>> expenseCategory = expenseDao.getTotalCost();
		List<Map<String, Object>> expenseUser = expenseDao.getTotalCostByUser();
			model.addAttribute("expenseCategory", expenseCategory);
		model.addAttribute("expenseUser", expenseUser);
		return "reports";
	}

	/**
	 *
	 * Page allows you to add an expense to the database.
	 * @return Redirects to the page to submit expenses.
	 */
	@GetMapping("/submitExpense")
	public String submitExpense(HttpSession session, Model model) {
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
	    return "showExpense";
	}

	/**
	 *
	 * Grabs all the data that was entered into the form and create a new expense. This expense
	 * is then added to the database.
	 * @return Redirects back to homepage.
	 * @throws IOException 
	 */
	@PostMapping("/createExpense")
	public String createExpense(@ModelAttribute("Expense") Expense createExpense, Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
			
		expenseDao.createExpense(createExpense, currentPrincipalName);

		List<Expense> expenses = expenseDao.getExpensesByUserName(currentPrincipalName);
        model.addAttribute("currentUserExpenses", expenses);
		return "homePage";
	}
	
	/*@PostMapping("/addImage")
	public String addImage(@ModelAttribute("Expense") Expense updateExpense, Model model) throws IOException{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
 
		System.out.println("VALUE OF NAmE: " + updateExpense.getId());
		System.out.println("VALUE OF bill image: " + updateExpense.getBillImage());
		
		expenseDao.createExpense(updateExpense, currentPrincipalName);
		
		String uploadDir = "../../../../../../../billImages/" + createExpense.getBillImage() + "-" + createExpense.getDate();
		
		File newFile2 = new File(createExpense.getBillImage());
		
		Path uploadPath = Paths.get(uploadDir);
        
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
       

		List<Expense> expenses = expenseDao.getExpensesByUserName(currentPrincipalName);
        model.addAttribute("currentUserExpenses", expenses);
		return "homePage";
	} */

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
		

		//BRING LIST
		List<Expense> expenses = expenseDao.getExpensesByUserNameAndExpenseType(currentPrincipalName, expenseType);

		 
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


		//BRING LIST
		if(sortExpense.equals("ASC"))
		{
			List<Expense> expenses = expenseDao.getExpensesBySortAsc(currentPrincipalName);

			//MODEL
			model.addAttribute("currentUserExpenses", expenses);
		}
		else if(sortExpense.equals("DESC"))
		{
			List<Expense> expenses = expenseDao.getExpensesBySortDesc(currentPrincipalName);

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

		//BRING LIST
		List<Expense> expenses = expenseDao.getExpensesByUserAndStatus(currentPrincipalName, expenseStatus);

		//MODEL
		model.addAttribute("currentUserExpenses", expenses);
		return "homePage";
	}
	
	/*
	 * 
	 * Import file
	 * @return Redirects to home page.
	 * /
	 */
	@PostMapping("/uploadExpensesFile")
    public String uploadCSVFile(@RequestParam("billImage") MultipartFile file, Model model) 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		Expense newExpense = new Expense();
		
		// validate file
        if (file.isEmpty()) 
        {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else 
        	{
        		String[] csvFileValue = new String[7];
        		
	        	 // parse CSV file to create a list of `User` objects
	            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) 
	            {
	                
	            	String line;
	            	
	            	//read lines of the file
	                while ((line = reader.readLine()) != null) {
	                	csvFileValue = line.split(",");
	                	
	                	//Setting the new expense
	                    newExpense.setExpenseName(csvFileValue[0]);
	                    newExpense.setExpenseCost(Integer.parseInt(csvFileValue[1]));
	                    newExpense.setDate(csvFileValue[2]);
	                    newExpense.setExpenseType(csvFileValue[3]);
	                    newExpense.setExpenseStatus(csvFileValue[4]);
	                    newExpense.setExpenseDesc("");
	                    newExpense.setBillImage("");
	                    newExpense.setUser(csvFileValue[7]);
	                    
	                    //Send the new expense to the Expense table in the database
	                    expenseDao.createExpense(newExpense, currentPrincipalName);
	                }
	                
	            } catch (Exception ex) 
	            {
            	
	            	model.addAttribute("message", "An error occurred while processing the CSV file.");
	                //model.addAttribute("status", false);
	            }
            
        	}
        
        List<Expense> expenses = expenseDao.getExpensesByUserName(currentPrincipalName);
        model.addAttribute("currentUserExpenses", expenses);
        
        return "homePage";
	}
	
	/**
	 * Redirects user to be able to import a csv file with expenses
	 * @return redirect to importExpensesCsvFile page.
	 */
	@GetMapping("/importCSV")
	public String importCSV(){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		USER_404_PROJECT user = userDAO.getUserByUserName(currentPrincipalName).get(0);

		return ("importExpensesCsvFile");
	}
}

