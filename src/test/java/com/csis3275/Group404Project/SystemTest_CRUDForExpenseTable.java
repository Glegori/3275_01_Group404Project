package com.csis3275.Group404Project;


import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.dao.userDAO;
import com.csis3275.Group404Project.model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
*   For the System Test we are testing the CRUD for the Expense Table,
*   We create a new expense in the create expense test, then do one of
*   CRUD operations in the later test (methods named appropriately).
 */


@SpringBootTest
public class SystemTest_CRUDForExpenseTable {

    @Autowired
    expenseDAO expenseDAO;

    @Autowired
    userDAO userDAO;

    Expense expense = new Expense();

    @Test
    public void createExpense(){
        expense.setExpenseName("NewExpense");
        expense.setExpenseCost(2000);
        expense.setDate("2020-12-01");
        expense.setExpenseType("Personal");
        expense.setExpenseStatus("Approved");
        expense.setBillImage("");
        expense.setUser("Shubham");

        assertTrue(expenseDAO.createExpense(expense, "Shubham"));

    }

    @Test
    public void readExpense(){
        assertEquals(userDAO.getUserByUserName("Shubham").get(0).getTotal(), expenseDAO.getTotalByUserName("Shubham"));
    }


    

}
