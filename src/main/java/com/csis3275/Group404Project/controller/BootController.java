package com.csis3275.Group404Project.controller;

import javax.servlet.http.HttpSession;

import com.csis3275.Group404Project.LoginUserDetailsService;
import com.csis3275.Group404Project.dao.userDAO;
import com.csis3275.Group404Project.model.USER_404_PROJECT;
import com.csis3275.Group404Project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.model.Expense;

import java.util.List;

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
	
	@ModelAttribute("Expense")
	public Expense setupAddForm() {
		return new Expense();
	}
	@ModelAttribute("User")
	public USER_404_PROJECT setupUserForm(){ return new USER_404_PROJECT();}

	@ModelAttribute("modExpense")
	public Expense setupModifyForm() {
		return new Expense();
	}
	//showLogin
	@GetMapping("/loginScreen")
	public String showLoginScreen(HttpSession session, Model model) {
		
		List<Expense> expense = expenseDao.getAllExpenses();

		model.addAttribute("loginScreen", expense);

	    return "loginScreen";
	}


	@GetMapping("/homePage")
	public String showHomePage(HttpSession session, Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		List<Expense> expenses = expenseDao.getExpensesByUserName(currentPrincipalName);

		model.addAttribute("currentUserExpenses", expenses);

		return "homePage";
	}
	
	@GetMapping("/decisionPage")
	public String showDecisionPage(HttpSession session, Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		List<Expense> expenses = expenseDao.getReportingExpenses(currentPrincipalName);
		model.addAttribute("reportingUserExpenses", expenses);

		return "decisionPage";
	}

	//checkLogin
	//	@PostMapping("/loginScreen")
//		public String loginScreen(@ModelAttribute("loginScreen") user checkUser, Model model)	{
			
			//do something
			 
	//		return "submitExpense";
	//	}
	//showForm
	@GetMapping("/submitExpense")
	public String submitExpense(HttpSession session, Model model) {
		
		//List<expense> expense = expenseDao.getAllExpenses();

		//model.addAttribute("loginScreen", expense);

	    return "submitExpense";
	}
	
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

	@GetMapping("/createUser")
	public String createUser(){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		User user = userDAO.getUserByUserName(currentPrincipalName).get(0);

		if(user.getUserType().equals("admin")){
			return ("createUser");
		} else {

			return("forbidden");

		}

	}
	@GetMapping("/forbidden")
	public String forbidden(){
		return ("forbidden");
	}


	@PostMapping("/submitUser")
	public String createNewUser(@ModelAttribute("User") USER_404_PROJECT createUser, Model model){

		userDAO.createUser(createUser);
		List<User> users = userDAO.getAllUsers();
		model.addAttribute("Users", users);

		return "createUser";
	}
	@GetMapping("/changePassword")
	public String changePasswordScreen(){
		return ("changePassword");
	}
	@PostMapping("/submitPassword")
	public String submitPassword(@RequestParam String oldPassword, @RequestParam String newPassword, Model model){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		User currentUser = userDAO.getUserByUserName(currentPrincipalName).get(0);

		if(oldPassword.equals(currentUser.getPassword())) {
			userDAO.updatePasswordByUserName(currentPrincipalName, newPassword);
			model.addAttribute("message", "Password for " + currentPrincipalName + " has been successfully updated!");
		} else {
			model.addAttribute("error", "Cannot update password for the current user. " +
					"Please try again, if the issue persists please contact an admin.");

		}

		return ("changePassword");
	}
}