package com.csis3275.Group404Project;


import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.dao.userDAO;
import com.csis3275.Group404Project.model.Expense;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
*   For the System Test we are testing the CRUD for the Expense Table,
*   We create a new expense in the create expense test, then do one of
*   CRUD operations in the later test (methods named appropriately).
*   @author = Shubham
 */


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SystemTest_CRUDForExpenseTable {

    @Autowired
    expenseDAO expenseDAO;

    @Autowired
    userDAO userDAO;

    Expense expense = new Expense();
    Boolean insertExpense = false;


    @BeforeAll
    public void assembleExpenseObject(){
        expense.setExpenseName("NewExpense");
        expense.setExpenseCost(2000);
        expense.setDate("2020-12-01");
        expense.setExpenseType("Personal");
        expense.setExpenseStatus("Approved");
        expense.setBillImage("");
        expense.setUser("Shubham");

        insertExpense = expenseDAO.createExpense(expense, "Shubham");
        System.out.println(expense.getId());
    }


    @Test
    public void createExpense(){
        assertTrue(insertExpense);

    }

    @Test
    public void readExpense(){
        assertEquals(userDAO.getUserByUserName("Shubham").get(0).getTotal(), expenseDAO.getTotalByUserName("Shubham"));
    }



    @Test
    public void updateExpense(){
        Expense modifyingExpense = expenseDAO.getExpensesByUserName("Shubham").get(0);
        modifyingExpense.setExpenseStatus("Denied");
        assertTrue(expenseDAO.modifyStatus(modifyingExpense));
    }

    @Test
    public void deleteExpense(){
        Expense deletingExpense = expenseDAO.getExpensesByUserName("Shubham").get(0);
        assertTrue(expenseDAO.deleteExpense(deletingExpense));
    }


}
