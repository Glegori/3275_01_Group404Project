package com.csis3275.Group404Project;

import static org.junit.jupiter.api.Assertions.*;

import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.model.Expense;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.csis3275.Group404Project.dao.userDAO;
import com.csis3275.Group404Project.model.USER_404_PROJECT;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Gregory Billings
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class IntegrationTest_getReportingExpenses {

    @Autowired
    expenseDAO expenseDAO;
    @Autowired
    userDAO userDAO;

    Expense newExpense = new Expense();
    USER_404_PROJECT newUser = new USER_404_PROJECT();

    /**
     * this is optimal because it only inserts one value into the database and only available because of the testinstance annotation
     */
    @BeforeAll
    public void setup(){
        addUsers();
        addExpense();
    }

    public void addUsers(){
        newUser.setUserName("TestUser");
        newUser.setPassword("12345");
        newUser.setReportsFrom("Francis,Shubham,Alfredo");
        newUser.setUserType("user");
        newUser.setTotal(20.00);
        userDAO.createUser(newUser);
    }

    public void addExpense(){
        newExpense.setExpenseName("TestExpense");
        newExpense.setExpenseCost(100);
        newExpense.setDate("2020-12-22");
        newExpense.setExpenseType("Personal");
        newExpense.setExpenseStatus("Pending");
        newExpense.setBillImage("fileString");
        expenseDAO.createExpense(newExpense, "Francis");
    }

    @Test
    public void testExpenseName(){
        List<Expense> list = expenseDAO.getReportingExpenses("TestUser");
        assertEquals("TestExpense", list.get(list.size()-1).getExpenseName());
    }

    @Test
    public void testExpenseCost(){
        List<Expense> list = expenseDAO.getReportingExpenses("TestUser");
        assertEquals(100, list.get(list.size()-1).getExpenseCost());
    }

    @Test
    public void testDate(){
        List<Expense> list = expenseDAO.getReportingExpenses("TestUser");
        assertEquals("2020-11-05", list.get(list.size()-1).getDate());
    }

    @Test
    public void testExpenseType(){
        List<Expense> list = expenseDAO.getReportingExpenses("TestUser");
        assertEquals("Personal", list.get(list.size()-1).getExpenseType());
    }

    @Test
    public void testExpenseStatus(){
        List<Expense> list = expenseDAO.getReportingExpenses("TestUser");
        assertEquals("Pending", list.get(list.size()-1).getExpenseStatus());
    }

    @Test
    public void testBillImage(){
        List<Expense> list = expenseDAO.getReportingExpenses("TestUser");
        assertEquals("fileString", list.get(list.size()-1).getBillImage());
    }

    @Test
    public void testUserName(){
        List<Expense> list = expenseDAO.getReportingExpenses("TestUser");
        assertEquals("Francis", list.get(list.size()-1).getUser());
    }
}