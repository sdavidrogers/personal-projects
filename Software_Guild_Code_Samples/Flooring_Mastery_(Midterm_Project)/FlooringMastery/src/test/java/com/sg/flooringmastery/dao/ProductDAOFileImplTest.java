package com.sg.flooringmastery.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ProductDAOFileImplTest {
    
    ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
    ProductDAO test = 
            ctx.getBean("productDao", ProductDAOFileImpl.class);
    
    public ProductDAOFileImplTest(){
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
    public void testGetProductPrices() throws Exception {
        ArrayList<BigDecimal> productPrices = test.getProductPrices("Carpet");
        assertEquals(2, productPrices.size());
        BigDecimal check = new BigDecimal("2.25");
        assertEquals(check, productPrices.get(0));
    }
    
    @Test
    public void testGetAllInfo() throws Exception {
        Map<String, ArrayList<BigDecimal>> infoList = test.getAllInfo();
        assertEquals(4, infoList.size());
    }
    
}
