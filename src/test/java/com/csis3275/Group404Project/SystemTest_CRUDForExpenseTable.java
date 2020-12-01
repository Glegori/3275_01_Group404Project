package com.csis3275.Group404Project;


import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.dao.userDAO;
import com.csis3275.Group404Project.model.Expense;
import com.csis3275.Group404Project.model.USER_404_PROJECT;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *   For the System Test we are testing the CRUD for the Expense Table,
 *   We create a new expense in the create expense test, then do one of
 *   CRUD operations in the later test (methods named appropriately).
 *   @author = Shubham
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SystemTest_CRUDForExpenseTable {

    @Autowired
    expenseDAO expenseDAO;

    @Autowired
    userDAO userDAO;

    USER_404_PROJECT user = new USER_404_PROJECT();
    Expense expense = new Expense();
    Boolean insertExpense = false;

    @BeforeAll
    public void assembleExpenseAndUserObjects() {
        //Assemble User
        user.setUserName("TestUser");
        user.setPassword("12345");
        user.setUserType("user");
        user.setReportsFrom("Francis,Alfredo,Shubham");
        user.setTotal(0);

        userDAO.createUser(user);

        //AssembleExpense
        expense.setExpenseName("NewExpense");
        expense.setExpenseCost(2000);
        expense.setDate("2020-12-22");
        expense.setExpenseType("Personal");
        expense.setExpenseStatus("Approved");
        expense.setBillImage("");
        expense.setUser(user.getUserName());

        insertExpense = expenseDAO.createExpense(expense, user.getUserName());
        System.out.println(expense.getId());
    }

    @Test
    @Order(1)
    public void createExpense() {
        assertTrue(insertExpense);
    }

    @Test
    @Order(2)
    public void readExpense() {
        assertEquals(userDAO.getUserByUserName(user.getUserName()).get(0).getTotal(), expenseDAO.getTotalByUserName(user.getUserName()));
    }

    @Test
    @Order(3)
    public void updateExpense() {
        Expense modifyingExpense = expenseDAO.getExpensesByUserName(user.getUserName()).get(0);
        modifyingExpense.setExpenseStatus("Denied");
        assertTrue(expenseDAO.modifyStatus(modifyingExpense));
    }

    @Test
    @Order(4)
    public void deleteExpense(){
        Expense deletingExpense = expenseDAO.getExpensesByUserName(user.getUserName()).get(0);
        assertTrue(expenseDAO.deleteExpense(deletingExpense));
    }
}
