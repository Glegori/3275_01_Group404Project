package com.csis3275.Group404Project.controller;

import javax.servlet.http.HttpSession;


import com.csis3275.Group404Project.csvHelper;
import com.csis3275.Group404Project.dao.userDAO;
import com.csis3275.Group404Project.model.CurrencyRate;
import com.csis3275.Group404Project.model.USER_404_PROJECT;

import antlr.StringUtils;

import com.csis3275.Group404Project.model.rates;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.model.Expense;

import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

import java.sql.Date;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	 * Saves our edit user to be used (this user data is subject to manipulation through the web application)
	 * @return
	 */
	@ModelAttribute("editUser")
	public USER_404_PROJECT setupEditUser() {
		return new USER_404_PROJECT();
	}

	/**
	 * Saves our current user to be used
	 * @return
	 */
	@ModelAttribute("currentUser")
	public USER_404_PROJECT setupCurrentUser() {
		return new USER_404_PROJECT();
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
		USER_404_PROJECT currentUser = userDAO.getUserByUserName(currentPrincipalName).get(0);

		RestTemplate restTemplate = new RestTemplate();
		CurrencyRate currencyRate = restTemplate.getForObject("http://localhost:6969/api", CurrencyRate.class);
		rates rates = currencyRate.getrates();
		System.out.println(currencyRate.getDate());
		System.out.println(rates);

		model.addAttribute("rates", rates);
		model.addAttribute("currencies", currencyRate);



		model.addAttribute("currentUser", currentUser);

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
	 * Page that allows to edit the budget of each category,
	 * @param model Holds all the current budgets.
	 * @return Redirects to the budget page.
	 */
	
	@GetMapping("/budget")
	public String budget(Model model) {
		List<Map<String, Object>> expenseBudget = expenseDao.getBudget();
		model.addAttribute("expenseBudget", expenseBudget);
		return "budget";	
		}
	
	/**
	 * Updates the selected budget based on the number inputed
	 * @param expenseType 
	 * @param budget
	 * @param model
	 * @return Redirects to budget page with updated values 
	 */
	@PostMapping("/saveBudget")
	public String saveBudget(@RequestParam String expenseType, @RequestParam double budget, Model model){
		
		expenseDao.updateBudget(expenseType, budget);
		
		List<Map<String, Object>> expenseBudget = expenseDao.getBudget();
		model.addAttribute("expenseBudget", expenseBudget);
		return "budget";
		
	}

	/**
	 * Updates the selected budget to -1 in the database.
	 * @param expenseTypeRemove
	 * @param model
	 * @return Redirects to budget page with updated values
	 */
	@PostMapping("/removeBudget")
	public String removeBudget(@RequestParam String expenseTypeRemove, Model model){
		
		expenseDao.updateBudget(expenseTypeRemove, -1.0);
		
		List<Map<String, Object>> expenseBudget = expenseDao.getBudget();
		model.addAttribute("expenseBudget", expenseBudget);
		return "budget";
		
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
	 * Grabs all the data that was entered into the form and create a new expense. Checks the budget too see if it will be auto
	 * declined. This expense is then added to the database.
	 * @return Redirects back to homepage.
	 * @throws IOException 
	 */
	@PostMapping("/createExpense")
	public String createExpense(@RequestParam("billImage") MultipartFile file,
			@RequestParam("expenseName") String expenseName, @RequestParam("expenseCost") double expenseCost, 
			@RequestParam("date") String date, @RequestParam("expenseType") String expenseType, 
			@RequestParam("expenseStatus") String expenseStatus, @RequestParam("expenseDesc") String expenseDesc,
			Model model){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
	
		USER_404_PROJECT currentUser = userDAO.getUserByUserName(currentPrincipalName).get(0);

		model.addAttribute("currentUser", currentUser);


		String UPLOADED_FOLDER = new File("src\\main\\uploadPhotos\\").getAbsolutePath();
		
		//check if the uploadPhotos folder exist
		File directory = new File(UPLOADED_FOLDER);
		if (!directory.exists()){
			directory.mkdirs();
		}
		
		//check if the file is empty
		if (file.isEmpty()) {
			
	            Expense createExpense = new Expense();
	            
	            createExpense.setExpenseName(expenseName);
	            createExpense.setDate(date);
	            createExpense.setExpenseCost(expenseCost);
	            createExpense.setExpenseType(expenseType);
	            createExpense.setExpenseStatus(expenseStatus);
	            createExpense.setBillImage("");
	            createExpense.setExpenseDesc(expenseDesc);
	            
	            double totalCost;
	            try {
	        		 totalCost = (double) expenseDao.getTotalCostForCategory(createExpense.getExpenseType()).get(0).get("TOTALCOST");
	            }
	            catch (Exception e) {
	            	 totalCost = 0;
	            }
	            try {
	        		double totalBudget = (double) expenseDao.getBudgetForCategory(createExpense.getExpenseType()).get(0).get("BUDGET");
	        		
	        		if (totalBudget != -1 && (totalCost + createExpense.getExpenseCost()) > totalBudget) {
	        			createExpense.setExpenseDesc("Declined due to expense going over budget");
	        			createExpense.setExpenseStatus("Denied");
	        		}
	        	}
	        	catch (Exception e) {
	        		
	        	}
	            
	            expenseDao.createExpense(createExpense, currentPrincipalName);
	
        }
		else
		{
			try {
				
				DateTimeFormatter dateAndTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
				LocalDateTime now = LocalDateTime.now();  
				String datePath = dateAndTime.format(now);
	            // Get the file and save it somewhere
	            byte[] bytes = file.getBytes();
	            String completePath = UPLOADED_FOLDER + "\\" + datePath + "-" + file.getOriginalFilename();
	            Path path = Paths.get(completePath);
	            Files.write(path, bytes);
	            
	            Expense createExpense = new Expense();
	            
	            String imagePath = datePath + "-" + file.getOriginalFilename();
	            createExpense.setExpenseName(expenseName);
	            createExpense.setDate(date);
	            createExpense.setExpenseCost(expenseCost);
	            createExpense.setExpenseType(expenseType);
	            createExpense.setExpenseStatus(expenseStatus);
	            createExpense.setBillImage(imagePath);
	            createExpense.setExpenseDesc(expenseDesc);
	            
	            double totalCost;
	            try {
	        		 totalCost = (double) expenseDao.getTotalCostForCategory(createExpense.getExpenseType()).get(0).get("TOTALCOST");
	            }
	            catch (Exception e) {
	            	 totalCost = 0;
	            }
	            try {
	        		double totalBudget = (double) expenseDao.getBudgetForCategory(createExpense.getExpenseType()).get(0).get("BUDGET");
	        		
	        		if (totalBudget != -1 && (totalCost + createExpense.getExpenseCost()) > totalBudget) {
	        			createExpense.setExpenseDesc("Declined due to expense going over budget");
	        			createExpense.setExpenseStatus("Denied");
	        		}
	        	}
	        	catch (Exception e) {
	        		
	        	}
	          
	            
	            expenseDao.createExpense(createExpense, currentPrincipalName);

			} catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		


      
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

	//MediaType mediaType = ;
	/**
	 *this returns your file with the excel file extension in byte form, only works when database is under 2gb after that switch copy to copyLarge
	 * @return your file in byte form
	 * @throws IOException
	 */
//	@GetMapping(value = "/excel")
//	public ResponseEntity fileReturn(){
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = authentication.getName();
//
//		USER_404_PROJECT user = userDAO.getUserByUserName(currentPrincipalName).get(0);
//
//		if(user.getUserType().equals("admin")) {
//			File excelFile = excelConverter.getExcelFile(expenseDao.getExpenseByStatus("Approved"));
//			return ResponseEntity.ok()
//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=data.xlsx")
//					.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//					.body(excelFile);
//		}
//		else {
//			File excelFile = excelConverter.getExcelFile(expenseDao.getExpensesByUserAndStatus(currentPrincipalName,"Approved"));
//			return ResponseEntity.ok()
//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=data.xlsx")
//					.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//					.body(excelFile);
//		}
//	}

	/**
	 *
	 * @return
	 */
	@GetMapping(value = "/csv", produces = "application/csv")
	public ResponseEntity fileReturn(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		USER_404_PROJECT user = userDAO.getUserByUserName(currentPrincipalName).get(0);

		if(user.getUserType().equals("admin")) {
			List<Expense> expenses = expenseDao.getExpenseByStatus("Approved");
			File csvFile = csvHelper.getCSVFile(expenses);
			InputStreamResource file = null;
			try {
				file = new InputStreamResource(new ByteArrayInputStream(Files.readAllBytes(Paths.get(csvFile.getPath()))));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=data.csv")
					.contentType(MediaType.parseMediaType("application/csv"))
					.body(file);
		}
		else {
			List<Expense> expenses = expenseDao.getExpensesByUserAndStatus(currentPrincipalName,"Approved");
			File csvFile = csvHelper.getCSVFile(expenses);
			InputStreamResource file = null;
			try {
				file = new InputStreamResource(new ByteArrayInputStream(Files.readAllBytes(Paths.get(csvFile.getPath()))));
			} catch (IOException e) {
				e.printStackTrace();
			}			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=data.csv")
					.contentType(MediaType.parseMediaType("application/csv"))
					.body(file);
		}
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
	 * @param STRING expenseType which is the type of the expense.
	 * @param MODEL model.
	 * @return Redirects to home page.
	 */
	@GetMapping("/filterExpense")
	public String filterExpense(@RequestParam(required = true) String expenseType, Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		List <Expense> expenses;

		//BRING LIST
			try {
			//BRING LIST
			expenses = expenseDao.getExpensesByUserNameAndExpenseType(currentPrincipalName, expenseType);
			}
			catch (Exception e) {
			expenses = null ;
			}
 
		//MODEL
		model.addAttribute("currentUserExpenses", expenses);
		return "homePage";
	}
	
	/**
	 *
	 * Shows a filtered list of expenses based on the sorting method selected.
	 * @param STRING sortExpense which is the type of the sort of the expense.
	 * @param MODEL model.
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
	 *@param STRING sortExpense which is the type of the sort of the expense.
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

	/**
	 * Shows the options for inputing the period in which the user wants to see the graphical reports for the expenses.
	 * @return Redirects to expensesOverTime page
	 */
	@GetMapping("/expensesOverTime")
	public String expensesOverTime(){
		return ("expensesByTime");
	}

	/**
	 * @param startDate is the starting date for the period in which the user wants to see the graphical reports for the expenses.
	 * @param endDate is the ending date for the period in which the user wants to see the graphical reports for the expenses.
	 * @return Redirects to expensesOverTime page
	 */
	@PostMapping("/expensesOverTime")
	public String displayExpensesOverTime(@RequestParam Date startDate, @RequestParam Date endDate, Model model){
		System.out.println(startDate);
//		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
//		Date date1 = formatter.parse(startDate);
		List<Expense> expenses = expenseDao.getExpensesBetweenDates(startDate, endDate);

		model.addAttribute("startDate", startDate.toString());
		model.addAttribute("endDate", endDate.toString());
		model.addAttribute("listOfExpensesBetweenDates",expenses);
		System.out.println(expenses.toString());

		return ("expensesByTimeGraphs");

	}

	/**
	 * Shows the table of users present in the database and their respective data fields some of which are editable through the edit form.
	 * @return Redirects to expensesOverTime page
	 */
	@GetMapping("/manageUsers")
    public String manageUsersPage(HttpSession session, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        USER_404_PROJECT user = userDAO.getUserByUserName(currentPrincipalName).get(0);

        if(user.getUserType().equals("admin")){

            List<USER_404_PROJECT> currentUsers = userDAO.getAllUsers();
            //System.out.println(currentUsers);
            model.addAttribute("currentUsers", currentUsers);

            return "manageUsers";
        } else {
            return("forbidden");
        }

    }
	
	/**
	 * Import Bill image to support the expense.
	 * @param MULTIFILE file which is the image file.
	 * @param Model model.
	 * @return Redirects to home page.
	 */
	@PostMapping("/uploadExpensesFile")
    public String uploadCSVFile(@RequestParam("billImage") MultipartFile file, Model model) 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		USER_404_PROJECT currentUser = userDAO.getUserByUserName(currentPrincipalName).get(0);

		model.addAttribute("currentUser", currentUser);


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
	                model.addAttribute("status", false);
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

	/**
	 * @param editUser is the user subject to the edit selected by an admin to edit the data fields for.
	 * @return Returns the manageUsers page but with a form which contains some default data according to the user that was
	 * selected for the edit.
	 */
	@PostMapping("/editUser")
	public String editUser(@ModelAttribute("editUser")USER_404_PROJECT editUser, Model model){

		userDAO.updateUserByID(editUser);
		List<USER_404_PROJECT> users = userDAO.getAllUsers();
		model.addAttribute("currentUsers", users);

		return "manageUsers";

	}

	/**
	 * @param id is the ID of the user that is going to be deleted.
	 * @return Redirects to manage users page but with an updated list of the users.
	 */
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam int id, Model model){

		userDAO.deleteUserByID(id);

		List<USER_404_PROJECT> users = userDAO.getAllUsers();
		model.addAttribute("currentUsers", users);


		return "manageUsers";
	}


	
	/**
	 *
	 * This method receive the path of the image and then search it in the database
	 * @param STRING pathImage which is the directory of the image.
	 * @param MODEL model.
	 * @return Redirects to show image page
	 */
	@GetMapping("/showImage")
	public String showImage(@RequestParam(required = true) String pathImage, Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		//BRING LIST
		List<Expense> expenses = expenseDao.getExpensesByImage(currentPrincipalName, pathImage);
		
		//MODEL
		model.addAttribute("currentUserExpenses", expenses);
		return "showImage";
	}
	
	/**
	 * Redirects user to be able to upload a bill image file
	 * @return redirect to updatebillImage page.
	 */
	@GetMapping("/updateImage")
	public String updateImage(@RequestParam("expenseID") int expenseID, Model model){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		List<Expense> expenses = expenseDao.getExpensesByID(expenseID);
		
		//MODEL
		model.addAttribute("currentUserExpenses", expenses);
		
		return ("updateImage");
	}
	
	/**
	 *
	 * Grabs expense id and expense bill image to update the expense 
	 * is then added to the database.
	 * @param MultipartFile file which is the bill image file
	 * @param INT id which is the expense ID
	 * @param MODEL model.
	 * @return Redirects back to homepage.
	 * @throws IOException 
	 */
	@PostMapping("/uploadExpensesImage")
	public String uploadExpensesImage(@RequestParam("billImage") MultipartFile file,
			@RequestParam("id") int id, Model model){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		USER_404_PROJECT currentUser = userDAO.getUserByUserName(currentPrincipalName).get(0);

		model.addAttribute("currentUser", currentUser);


		String UPLOADED_FOLDER = new File("src\\main\\uploadPhotos\\").getAbsolutePath();
	    
		//check if the uploadPhotos folder exist
		File directory = new File(UPLOADED_FOLDER);
		if (!directory.exists()){
			directory.mkdirs();
		}
		
		//check if the file is empty
		if (file.isEmpty()) {
           //redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "homePage";
       }
		
		try {
			
			DateTimeFormatter dateAndTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
			LocalDateTime now = LocalDateTime.now();  
			String datePath = dateAndTime.format(now);
           // Get the file and save it somewhere
           byte[] bytes = file.getBytes();
           String completePath = UPLOADED_FOLDER +"\\" + datePath + "-" + file.getOriginalFilename();
           Path path = Paths.get(completePath);
           Files.write(path, bytes);
           
           Expense updateExpense = new Expense();
           
           String imagePath = datePath + "-" + file.getOriginalFilename();
           updateExpense.setBillImage(imagePath);
           updateExpense.setId(id);
           expenseDao.updateImage(updateExpense);

		} catch (IOException e) {
           e.printStackTrace();
       }

     
		List<Expense> expenses = expenseDao.getExpensesByUserName(currentPrincipalName);
       model.addAttribute("currentUserExpenses", expenses);
		return "homePage";
	}

}

