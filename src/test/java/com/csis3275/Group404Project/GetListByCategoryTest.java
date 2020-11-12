package com.csis3275.Group404Project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.csis3275.Group404Project.dao.expenseDAO;

/**
 * Tests that the expenses are returned.
 * @author Francis
 *
 */
@SpringBootTest
public class GetListByCategoryTest {

    @Autowired
    expenseDAO dao;

    @Test
    public void getExpenseType(){
        assertEquals("Personal", dao.getTotalCost().get(0).get("EXPENSETYPE"));
    }
}
