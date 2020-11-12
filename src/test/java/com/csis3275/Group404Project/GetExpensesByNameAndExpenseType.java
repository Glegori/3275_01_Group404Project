package com.csis3275.Group404Project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.csis3275.Group404Project.dao.expenseDAO;

/**
 * Tests that the expenses are returned.
 * @author Alfredo Morales
 *
 */
@SpringBootTest
public class GetExpensesByNameAndExpenseType {

    @Autowired
    expenseDAO dao;

    @Test
    public void getExpenseType(){
    	 assertEquals("FOOD", dao.getExpensesByUserNameAndExpenseType("Alfredo","FOOD").get(0).getExpenseType());
    }
}
