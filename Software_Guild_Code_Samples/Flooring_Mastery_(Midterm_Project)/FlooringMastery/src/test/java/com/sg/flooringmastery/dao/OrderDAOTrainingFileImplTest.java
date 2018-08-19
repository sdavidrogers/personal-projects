package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class OrderDAOTrainingFileImplTest {
    
    ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
    OrderDAO test = 
            ctx.getBean("orderDaoTraining", OrderDAOTrainingFileImpl.class);
    
    public OrderDAOTrainingFileImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        Map<String, Map<Integer, Order>> outer = test.getAllOrders();
        for (String date : outer.keySet()) {
            for (Integer orderNum : outer.get(date).keySet()) {
                test.removeOrder(date, orderNum);
            }
        }
    }
    
    @After
    public void tearDown() throws Exception {
        Map<String, Map<Integer, Order>> outer = test.getAllOrders();
        for (String date : outer.keySet()) {
            for (Integer orderNum : outer.get(date).keySet()) {
                test.removeOrder(date, orderNum);
            }
        }
        //test.saveWork();
    }

    @Test
    public void testAddOrderGetOrderByNumber() throws Exception {
        Order orderOne = new Order();
        Tax tax = new Tax();
        Product product = new Product();
        orderOne.setOrderNumber(1);
        orderOne.setCustomerName("Sam Rogers");
        tax.setState("OH");
        tax.setRate(new BigDecimal("6.25"));
        orderOne.setTaxInfo(tax);
        product.setType("Carpet");
        orderOne.setArea(40);
        product.setCostPerSquareFoot(new BigDecimal("2.25"));
        product.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        orderOne.setProductInfo(product);
        orderOne.setLaborCost(orderOne.getProductInfo().getLaborCostPerSquareFoot()
                .multiply(BigDecimal.valueOf(orderOne.getArea())));
        orderOne.setMaterialCost(orderOne.getProductInfo().getCostPerSquareFoot()
                .multiply(BigDecimal.valueOf(orderOne.getArea())));
        orderOne.setTax((orderOne.getMaterialCost().divide(orderOne.getTaxInfo().getRate())
                .add(orderOne.getLaborCost().divide(orderOne.getTaxInfo().getRate()))));
        orderOne.setTotal(orderOne.getTax().add(orderOne.getLaborCost()
                .add(orderOne.getMaterialCost())));
        orderOne.setOrderDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)));
        test.addOrder(orderOne);
        Order orderTwo = test.getOrderByNumber(orderOne.getOrderDate().toString(), orderOne.getOrderNumber());
        assertEquals(orderOne, orderTwo);
    }
    
    @Test
    public void testRemoveOrderGetOrdersByDate() throws Exception {
        Order orderOne = new Order();
        Tax tax = new Tax();
        Product product = new Product();
        orderOne.setOrderNumber(1);
        orderOne.setCustomerName("Sam Rogers");
        tax.setState("OH");
        tax.setRate(new BigDecimal("6.25"));
        orderOne.setTaxInfo(tax);
        product.setType("Carpet");
        orderOne.setArea(40);
        product.setCostPerSquareFoot(new BigDecimal("2.25"));
        product.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        orderOne.setProductInfo(product);
        orderOne.setLaborCost(orderOne.getProductInfo().getLaborCostPerSquareFoot()
                .multiply(BigDecimal.valueOf(orderOne.getArea())));
        orderOne.setMaterialCost(orderOne.getProductInfo().getCostPerSquareFoot()
                .multiply(BigDecimal.valueOf(orderOne.getArea())));
        orderOne.setTax((orderOne.getMaterialCost().divide(orderOne.getTaxInfo().getRate())
                .add(orderOne.getLaborCost().divide(orderOne.getTaxInfo().getRate()))));
        orderOne.setTotal(orderOne.getTax().add(orderOne.getLaborCost()
                .add(orderOne.getMaterialCost())));
        orderOne.setOrderDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)));
        test.addOrder(orderOne);
        
        Order orderTwo = new Order();
        Tax tax1 = new Tax();
        Product product1 = new Product();
        orderTwo.setOrderNumber(2);
        orderTwo.setCustomerName("Sam Rogers");
        tax1.setState("OH");
        tax1.setRate(new BigDecimal("6.25"));
        orderTwo.setTaxInfo(tax1);
        product1.setType("Carpet");
        orderTwo.setArea(40);
        product1.setCostPerSquareFoot(new BigDecimal("2.25"));
        product1.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        orderTwo.setProductInfo(product1);
        orderTwo.setLaborCost(orderTwo.getProductInfo().getLaborCostPerSquareFoot()
                .multiply(BigDecimal.valueOf(orderTwo.getArea())));
        orderTwo.setMaterialCost(orderTwo.getProductInfo().getCostPerSquareFoot()
                .multiply(BigDecimal.valueOf(orderTwo.getArea())));
        orderTwo.setTax((orderTwo.getMaterialCost().divide(orderTwo.getTaxInfo().getRate())
                .add(orderTwo.getLaborCost().divide(orderTwo.getTaxInfo().getRate()))));
        orderTwo.setTotal(orderTwo.getTax().add(orderTwo.getLaborCost()
                .add(orderTwo.getMaterialCost())));
        orderTwo.setOrderDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)));
        test.addOrder(orderTwo);
        
        Map<Integer, Order> check = test.getOrdersByDate(orderOne.getOrderDate().toString());
        assertEquals(2, check.size());
        
        Order newOrder = test.removeOrder(orderOne.getOrderDate().toString(), orderOne.getOrderNumber());
        assertEquals(orderOne, newOrder);
        
        check = test.getOrdersByDate(orderOne.getOrderDate().toString());
        assertEquals(1, check.size());
        
        test.removeOrder(orderTwo.getOrderDate().toString(), orderTwo.getOrderNumber());
        
        assertTrue(test.getAllOrders().isEmpty());
    }
    
    @Test
    public void testEditOrder() throws Exception {
        Order orderOne = new Order();
        Tax tax = new Tax();
        Product product = new Product();
        orderOne.setOrderNumber(1);
        orderOne.setCustomerName("Sam Rogers");
        tax.setState("OH");
        tax.setRate(new BigDecimal("6.25"));
        orderOne.setTaxInfo(tax);
        product.setType("Carpet");
        orderOne.setArea(40);
        product.setCostPerSquareFoot(new BigDecimal("2.25"));
        product.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        orderOne.setProductInfo(product);
        orderOne.setLaborCost(orderOne.getProductInfo().getLaborCostPerSquareFoot()
                .multiply(BigDecimal.valueOf(orderOne.getArea())));
        orderOne.setMaterialCost(orderOne.getProductInfo().getCostPerSquareFoot()
                .multiply(BigDecimal.valueOf(orderOne.getArea())));
        orderOne.setTax((orderOne.getMaterialCost().divide(orderOne.getTaxInfo().getRate())
                .add(orderOne.getLaborCost().divide(orderOne.getTaxInfo().getRate()))));
        orderOne.setTotal(orderOne.getTax().add(orderOne.getLaborCost()
                .add(orderOne.getMaterialCost())));
        orderOne.setOrderDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)));
        
        Order orderTwo = test.addOrder(orderOne);
        
        orderTwo.setCustomerName("Sammy Baby");
        
        test.editOrder(orderOne, orderTwo);
        Order newOrder = test.getOrderByNumber(orderOne.getOrderDate().toString(), orderOne.getOrderNumber());
        
        assertEquals(orderTwo, newOrder);
    }
    
    @Test
    public void testSaveWorkReadOrders() throws Exception {
        //saveWork is called in case there are already orders stored and the training version only
        // clears the memory map and loads the files
        test.saveWork();
        Map<String, Map<Integer, Order>> check = test.getAllOrders();
        Order orderOne = new Order();
        Tax tax = new Tax();
        Product product = new Product();
        orderOne.setOrderNumber(1);
        orderOne.setCustomerName("Sam Rogers");
        tax.setState("OH");
        tax.setRate(new BigDecimal("6.25"));
        orderOne.setTaxInfo(tax);
        product.setType("Carpet");
        orderOne.setArea(40);
        product.setCostPerSquareFoot(new BigDecimal("2.25"));
        product.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        orderOne.setProductInfo(product);
        orderOne.setLaborCost(orderOne.getProductInfo().getLaborCostPerSquareFoot()
                .multiply(BigDecimal.valueOf(orderOne.getArea())));
        orderOne.setMaterialCost(orderOne.getProductInfo().getCostPerSquareFoot()
                .multiply(BigDecimal.valueOf(orderOne.getArea())));
        orderOne.setTax((orderOne.getMaterialCost().divide(orderOne.getTaxInfo().getRate())
                .add(orderOne.getLaborCost().divide(orderOne.getTaxInfo().getRate()))));
        orderOne.setTotal(orderOne.getTax().add(orderOne.getLaborCost()
                .add(orderOne.getMaterialCost())));
        orderOne.setOrderDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)));
        
        test.addOrder(orderOne);
        
        test.saveWork();
        
        assertEquals(test.getAllOrders().size(), check.size());
    }
    
    @Test
    public void testOrderNotFoundException() throws Exception {
        try {
            test.getOrdersByDate(LocalDate.MAX.format(DateTimeFormatter.ISO_LOCAL_DATE));
            fail("Expected OrderNotFoundException was not thrown.");
        } catch (OrderNotFoundException e) {}
        
        try {
            test.getOrderByNumber(LocalDate.MAX.format(DateTimeFormatter.ISO_LOCAL_DATE), -1);
            fail("Expected OrderNotFoundException was not thrown.");
        } catch (OrderNotFoundException e) {}
    }
    
}
