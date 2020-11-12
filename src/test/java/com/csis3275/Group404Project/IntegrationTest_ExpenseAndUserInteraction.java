package com.csis3275.Group404Project;

import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.dao.userDAO;
import com.csis3275.Group404Project.model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IntegrationTest_ExpenseAndUserInteraction {

/**    For this test we are testing the interaction between two tables in our database
*    which in further uses two DAOs (Expense and User) and two entities/models.
*    Since all of our data is stored in database, we have to access that database to check with consistency.
*    One could argue this is not a true integration test because we are just redundantly checking the integrity of the
*    database but we are not sure what else to do for integrity tests.
*   @author Shubham
*/
    @Autowired
    userDAO userDAO;

    @Autowired
    expenseDAO expenseDAO;

    Expense expense = new Expense();


    @BeforeEach
    public void addExpense(){
        expense.setExpenseName("NewTestExpense");
        expense.setDate("2020-10-11");
        expense.setExpenseType("Personal");
        expense.setExpenseStatus("Denied");
        expense.setBillImage("");
        expense.setUser("Shubham");
        expense.setExpenseCost(2200);
    }


    @Test
    //We currently don't have a constraint to update the User table total and a new expense being added, 
    //we use the interaction between the DAOs to update the table.
    public void totalTest(){
        expenseDAO.createExpense(expense, "Shubham");

        assertEquals(2200, userDAO.getUserByUserName("Shubham").get(0).getTotal());
    }

}
