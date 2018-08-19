package com.sg.flooringmastery.dao;

import java.math.BigDecimal;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TaxDAOFileImplTest {
    
    ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
    TaxDAO test = 
            ctx.getBean("taxDao", TaxDAOFileImpl.class);
    
    public TaxDAOFileImplTest() {
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
    public void testGetStateTaxRate() throws Exception {
        BigDecimal taxRate = test.getStateTaxRate("OH");
        BigDecimal check = new BigDecimal("6.25");
        assertEquals(check, taxRate);
    }
    
    @Test
    public void testGetAllInfo() throws Exception {
        Map<String, BigDecimal> infoList = test.getAllInfo();
        assertEquals(4, infoList.size());
    }
    
}
