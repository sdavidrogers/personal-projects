package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.util.HashMap;
import java.util.Map;


public class OrderDAOStubImpl implements OrderDAO {

    private final Map<String, Map<Integer, Order>> orders = new HashMap<>();
    
 
    
    @Override
    public Order addOrder(Order order) {
        if (orders.get(order.getOrderDate().toString()) == null) {
            orders.put(order.getOrderDate().toString(), new HashMap(){{put(order.getOrderNumber(), order);}});
        } else {
            orders.get(order.getOrderDate().toString()).put(order.getOrderNumber(), order);
        }
        return order;
    }

    
    
    @Override
    public Order getOrderByNumber(String date, int orderNumber) throws OrderNotFoundException {
        Map<Integer, Order> ordersByDate = getOrdersByDate(date);
        if (ordersByDate.get(orderNumber) == null) {
            throw new OrderNotFoundException("No orders matching input date.");
        } else {
            return ordersByDate.get(orderNumber);
        }
    }

    
    
    @Override
    public Map<Integer, Order> getOrdersByDate(String date) throws OrderNotFoundException {
        if (orders.get(date) == null || orders.get(date).isEmpty()) {
            throw new OrderNotFoundException("No orders matching input date.");
        } else {
            return orders.get(date);
        }
    }

    
    
    @Override
    public Map<String, Map<Integer, Order>> getAllOrders() {
        Map<String, Map<Integer, Order>> temp = new HashMap<>();
        for (String date : orders.keySet()) {
            for (Order order : orders.get(date).values()) {
                Order tempOrder = new Order();
                tempOrder.setOrderDate(order.getOrderDate());
                tempOrder.setOrderNumber(order.getOrderNumber());
                tempOrder.setCustomerName(order.getCustomerName());
                tempOrder.setTaxInfo(order.getTaxInfo());
                tempOrder.setProductInfo(order.getProductInfo());
                tempOrder.setArea(order.getArea());
                tempOrder.setMaterialCost(order.getMaterialCost());
                tempOrder.setLaborCost(order.getLaborCost());
                tempOrder.setTax(order.getTax());
                tempOrder.setTotal(order.getTotal());
                if (temp.get(tempOrder.getOrderDate().toString()) == null) {
                    temp.put(tempOrder.getOrderDate().toString(), new HashMap(){{put(tempOrder.getOrderNumber(), tempOrder);}});
                } else {
                    temp.get(tempOrder.getOrderDate().toString()).put(tempOrder.getOrderNumber(), tempOrder);
                }
            }
        }
        return temp;
    }

    
    
    @Override
    public Order editOrder(Order oldOrder, Order newOrder) throws OrderNotFoundException {
        this.addOrder(newOrder);
        if (!oldOrder.getOrderDate().equals(newOrder.getOrderDate())) {
            this.removeOrder(oldOrder.getOrderDate().toString(), oldOrder.getOrderNumber());
        }
        return newOrder;
    }

    
    
    @Override
    public Order removeOrder(String date, int orderNumber) throws OrderNotFoundException {
        Order temp = this.getOrderByNumber(date, orderNumber);
        orders.get(date).remove(orderNumber);
        if (orders.get(date).isEmpty()) {
            orders.remove(date);
        }
        return temp;
    }

    
    
    @Override
    public void saveWork() throws FilePersistenceException, OrderNotFoundException {}
    
}
