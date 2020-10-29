package com.csis3275.Group404Project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.model.Expense;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BootController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@Autowired
	expenseDAO expenseDao;
	
	@ModelAttribute("expenses")
	public Expense setupAddForm() {
		return new Expense();
	}
	
	//showLogin
	@GetMapping("/loginScreen")
	public String showLoginScreen(HttpSession session, Model model) {
		
		//List<expense> expense = expenseDao.getAllExpenses();

		//model.addAttribute("loginScreen", expense);

	    return "loginScreen";
	}


	@GetMapping("/homePage")
	public String showHomePage(HttpSession session, Model model) {

		//List<expense> expense = expenseDao.getAllExpenses();

		//model.addAttribute("loginScreen", expense);

		return "homePage";
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
	@PostMapping("/submitExpense")
	public String createExpense(@ModelAttribute("expense") Expense createExpense, Model model)	{
		
		 return "showExpense";
	}

	
	
}