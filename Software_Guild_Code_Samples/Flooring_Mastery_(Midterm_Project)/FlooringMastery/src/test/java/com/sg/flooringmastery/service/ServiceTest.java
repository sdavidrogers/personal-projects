package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServiceTest {
    
    ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
    Service test = 
            ctx.getBean("service", Service.class);
    
    public ServiceTest() {
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
    public void testCreateOrder() throws Exception {
        Order orderOne = new Order();
        Tax tax = new Tax();
        Product product = new Product();
        orderOne.setOrderDate(LocalDate.parse("2017-04-29"));
        orderOne.setCustomerName("Sammy Baby");
        tax.setState("OH");
        orderOne.setTaxInfo(tax);
        product.setType("Carpet");
        orderOne.setProductInfo(product);
        orderOne.setArea(40);
        
        Order orderOneCheck = test.generateOrder(orderOne);
        assertNotNull(orderOneCheck.getTaxInfo().getRate());
        assertNotNull(orderOneCheck.getProductInfo().getCostPerSquareFoot());
        assertNotNull(orderOneCheck.getProductInfo().getLaborCostPerSquareFoot());
        assertNotNull(orderOneCheck.getMaterialCost());
        assertNotNull(orderOneCheck.getLaborCost());
        assertNotNull(orderOneCheck.getTax());
        assertNotNull(orderOneCheck.getTotal());
    }
    
    @Test
    public void testConfirmOrderGetOrderByNumber() throws Exception {
        Order orderOne = new Order();
        Tax tax = new Tax();
        Product product = new Product();
        orderOne.setOrderDate(LocalDate.parse("2017-04-29"));
        orderOne.setCustomerName("Sammy Baby");
        tax.setState("OH");
        orderOne.setTaxInfo(tax);
        product.setType("Carpet");
        orderOne.setProductInfo(product);
        orderOne.setArea(40);
        
        orderOne = test.generateOrder(orderOne);
        
        test.addOrder(orderOne);
        
        assertEquals(orderOne, test.getOrderByNumber(orderOne.getOrderDate().toString(), orderOne.getOrderNumber()));
        test.removeOrder(orderOne);
    }
    
    @Test
    public void testCreateOrderInvalidOrderStateException() throws Exception {
        Order orderOne = new Order();
        Tax tax = new Tax();
        Product product = new Product();
        orderOne.setOrderDate(LocalDate.parse("2017-04-29"));
        orderOne.setCustomerName("Sammy Baby");
        tax.setState("OH");
        orderOne.setTaxInfo(tax);
        product.setType("Carpet");
        orderOne.setProductInfo(product);
        // Area omitted intentionally to trigger exception
        
        try {
            test.generateOrder(orderOne);
            fail("Expected InvalidOrderStateException.");
        } catch (InvalidOrderStateException e) {}
    }
    
    @Test
    public void testRemoveOrder() throws Exception {
        Order orderOne = new Order();
        Tax tax = new Tax();
        Product product = new Product();
        orderOne.setCustomerName("Sammy Baby");
        orderOne.setOrderDate(LocalDate.parse("2017-04-29"));
        tax.setState("OH");
        orderOne.setTaxInfo(tax);
        product.setType("Carpet");
        orderOne.setProductInfo(product);
        orderOne.setArea(40);
        
        orderOne = test.generateOrder(orderOne);
        test.addOrder(orderOne);
        
        assertEquals(orderOne, test.removeOrder(test.getOrderByNumber(orderOne.getOrderDate().toString(), orderOne.getOrderNumber())));
    }
    
    @Test
    public void testEditOrder() throws Exception {
        Order orderOne = new Order();
        Tax tax = new Tax();
        Product product = new Product();
        orderOne.setOrderDate(LocalDate.parse("2017-04-29"));
        orderOne.setCustomerName("Sammy Baby");
        tax.setState("OH");
        orderOne.setTaxInfo(tax);
        product.setType("Carpet");
        orderOne.setProductInfo(product);
        orderOne.setArea(40);
        orderOne = test.generateOrder(orderOne);
        
        Order orderTwo = test.addOrder(orderOne);
        
        orderTwo.setCustomerName("Smoochy Baby");
        test.editOrder(orderOne, orderTwo);
        assertEquals(orderTwo, test.getOrderByNumber(orderOne.getOrderDate().toString(), orderOne.getOrderNumber()));
        test.removeOrder(orderOne);
    }
    
    @Test
    public void testGetOrdersByDate() throws Exception {
        Order orderOne = new Order();
        Tax tax = new Tax();
        Product product = new Product();
        orderOne.setOrderDate(LocalDate.parse("2017-04-29"));
        orderOne.setCustomerName("Sammy Baby");
        tax.setState("OH");
        orderOne.setTaxInfo(tax);
        product.setType("Carpet");
        orderOne.setProductInfo(product);
        orderOne.setArea(40);
        orderOne = test.generateOrder(orderOne);
        test.addOrder(orderOne);
        
        Order orderTwo = new Order();
        Tax tax1 = new Tax();
        Product product1 = new Product();
        orderTwo.setOrderDate(LocalDate.parse("2017-04-29"));
        orderTwo.setCustomerName("Sammy Baby");
        tax1.setState("OH");
        orderTwo.setTaxInfo(tax1);
        product1.setType("Carpet");
        orderTwo.setProductInfo(product1);
        orderTwo.setArea(40);
        orderTwo = test.generateOrder(orderTwo);
        test.addOrder(orderTwo);
        
        assertEquals(2, test.getOrdersByDate(orderTwo.getOrderDate().toString()).size());
        
        test.removeOrder(orderOne);
        test.removeOrder(orderTwo);
    }
    
    @Test
    public void testGetAllProductInfo() {
        assertNotNull(test.getAllProductInfo());
    }
    
    @Test
    public void testGetAllTaxInfo() {
        assertNotNull(test.getAllTaxInfo());
    }
    
}
