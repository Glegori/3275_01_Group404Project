package com.csis3275.Group404Project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.model.Expense;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BootController {

//	@RequestMapping("/")
//	public String index() {
//		return "Greetings from Spring Boot!";
//	}
//
	@Autowired
	expenseDAO expenseDao;
	
	@ModelAttribute("Expense")
	public Expense setupAddForm() {
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
	
	@GetMapping("/barGraph")
	public String barGraph(Model model) {
		List<Map<String, Object>> expenseCost = expenseDao.getTotalCost();
		model.addAttribute("expenseCost", expenseCost);
		return "barGraph";
		
	}


	//checkLogin
	//	@PostMapping("/loginScreen")
//		public String loginScreen(@ModelAttribute("loginScreen") user checkUser, Model model)	{
			
			//do something
			 
	//		return "submitExpense";
	//	}
	
	
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
	
	
	
}