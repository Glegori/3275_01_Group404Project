package com.csis3275.Group404Project;

import static org.junit.jupiter.api.Assertions.*;

import com.csis3275.Group404Project.model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.csis3275.Group404Project.dao.expenseDAO;

import java.sql.Date;

/**
 * Tests that the expenses are returned.
 * @author Alfredo Morales
 *
 */
@SpringBootTest
public class GetExpensesByNameAndExpenseType {

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
    public void getExpenseType(){
    	 assertEquals("FOOD", dao.getExpensesByUserNameAndExpenseType("Alfredo","FOOD").get(0).getExpenseType());
    }
}
