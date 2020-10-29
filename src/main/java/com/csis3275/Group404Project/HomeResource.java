    public String admin(){
package com.csis3275.Group404Project;


import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csis3275.model_amo_93.Pizza_amo_93;

import antlr.collections.List;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String home(){
        return ("<h1>Welcome<h1>");
    }

    @GetMapping("/submitExpenses")
	public String submitExpenses(HttpSession session, Model model)
    {
		
		//List<Expense> expense = ExpenseDao.getAllExpenses();

		//model.addAttribute("loginScreen", expense);

	    return "submitExpenses";
	}
    
    @PostMapping("/createExpense")
	public String createExpense(@ModelAttribute("createExpense") Expense createExpense, Model model)	{
		
		//Create the car pass the object in.
		ExpenseDao.createExpense(createExpense);
		
		//Get a list of car from the controller
		List<Expense> expense = expenseDAO.getAllExpenses();
		model.addAttribute("expenses", expense);
		
		return "showExpenses";
	}
    
    
       /* public String user(){
            return ("<h1>Welcome User</h1>");
        }*/

    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Welcome Admin</h1>");
    }

    

}

