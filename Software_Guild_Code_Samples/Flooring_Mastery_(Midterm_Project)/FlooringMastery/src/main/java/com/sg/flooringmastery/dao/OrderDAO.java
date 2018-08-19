package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.util.Map;


public interface OrderDAO {
    
    public Order addOrder(Order order);
    
    public Order getOrderByNumber(String date, int orderNumber) throws OrderNotFoundException;
    
    public Map<Integer, Order> getOrdersByDate(String date) throws OrderNotFoundException;
    
    public Map<String, Map<Integer, Order>> getAllOrders();
    
    public Order editOrder(Order oldOrder, Order newOrder) throws OrderNotFoundException;
    
    public Order removeOrder(String date, int orderNumber) throws OrderNotFoundException;
    
    public void saveWork() throws FilePersistenceException, OrderNotFoundException;
      
}
