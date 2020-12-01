package com.csis3275.Group404Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.model.Expense;

import java.sql.Date;

/**
 * Tests that the expenses are returned with status.
 * @author Alfredo
 *
 */
class GetExpensesByStatus {

	 @Autowired
	    expenseDAO dao;
	    Expense expense = new Expense();

	    @BeforeEach
	    public void addDummyExpense(){
	        expense.setExpenseName("Dinner");
	        expense.setExpenseCost(120);
	        expense.setDate("2020-12-22");
	        expense.setExpenseType("FOOD");
	        expense.setExpenseStatus("Approved");
	        expense.setBillImage("");
	        expense.setUser("Alfredo");
	        dao.createExpense(expense, "Alfredo");
	    }
	    @Test
	    public void getExpenseStatus(){
	    	 assertEquals("Pending", dao.getExpensesByUserAndStatus("Alfredo", "Pending").get(0).getExpenseStatus());
	    }
}
