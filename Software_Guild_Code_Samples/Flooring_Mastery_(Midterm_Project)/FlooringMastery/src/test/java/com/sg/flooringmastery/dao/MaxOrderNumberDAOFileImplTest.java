package com.sg.flooringmastery.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MaxOrderNumberDAOFileImplTest {
    
    ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
    MaxOrderNumberDAO test = 
            ctx.getBean("orderNumDao", MaxOrderNumberDAOFileImpl.class);
    
    public MaxOrderNumberDAOFileImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetMaxOrderNumber() throws Exception {
        int maxOrderNumberOne = test.getMaxOrderNumber();
        int maxOrderNumberTwo = test.getMaxOrderNumber();
        assertEquals(maxOrderNumberTwo, maxOrderNumberOne + 1);
    }
    
}
