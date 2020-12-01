package com.csis3275.Group404Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.model.Expense;

import java.sql.Date;

/**
 * Tests that the expenses are returned sorted.
 * @author Alfredo
 *
 */
class GetExpensesBySort {

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
	    public void getExpenseTypeAsc(){
	    	 assertEquals("Approved", dao.getExpensesBySortAsc("Alfredo").get(0).getExpenseStatus());
	    }
	    
	    @Test
	    public void getExpenseTypeDesc(){
	    	 assertEquals("Pending", dao.getExpensesBySortDesc("Alfredo").get(0).getExpenseStatus());
	    }
}
