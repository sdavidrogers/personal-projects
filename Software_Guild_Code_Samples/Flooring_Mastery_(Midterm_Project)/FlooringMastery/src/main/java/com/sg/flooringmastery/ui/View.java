package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;


public interface View {
    
    public int printMenuAndGetSelection();
    
    public Order getNewOrderInfo(Map<String, ArrayList<BigDecimal>> productInfo, 
            Map<String, BigDecimal> taxInfo);
    
    public Order getEditOrderInfo(Order order, Map<String, ArrayList<BigDecimal>> productInfo,
            Map<String, BigDecimal> taxInfo);
    
    public void displayErrorMessage(String message);
    
    public boolean confirmOrderDetails(Order order);
    
    public boolean confirmAddOrderDetails(Order order);
    
    public void displayOrder(Order order);
    
    public void displayAllOrdersBanner();
    
    public void displayOrdersByDateBanner();
    
    public void removeOrderBanner();
    
    public void displayOrderAddedMessage();
    
    public void displayActionCancelled();
    
    public String getDateInput();
    
    public int getOrderNumberInput();
    
    public void displayOrderRemoved();
    
    public void displayOrderEdited();
    
    public void displayAllOrders(Map<String, Map<Integer, Order>> orders);
    
    public void displayOrdersByDate(Map<Integer, Order> orders);
    
    public void displayProductInfo(Map<String, ArrayList<BigDecimal>> productInfo);
    
    public void displayTaxInfo(Map<String, BigDecimal> taxInfo);
    
    public void workSavedMessage();
    
    public void displayUnknownCommandMessage();
    
    public void displayExitMessage();
    
}
