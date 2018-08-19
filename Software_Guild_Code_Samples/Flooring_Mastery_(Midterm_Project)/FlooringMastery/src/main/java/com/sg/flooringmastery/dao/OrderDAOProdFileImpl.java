package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class OrderDAOProdFileImpl implements OrderDAO {
    
    
    
    private final OrderFileListDAO orderFileList = new OrderFileListDAOFileImpl();
    private static final String DELIMITER = ",";
    private final Map<String, Map<Integer, Order>> orders = new HashMap<>();
    
    
    
    public OrderDAOProdFileImpl() throws FilePersistenceException {
        loadOrders();
    }

    
    
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
            throw new OrderNotFoundException("No orders matching input number.");
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
                Order tempOrder = copyOrder(order);
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
        this.removeOrder(oldOrder.getOrderDate().toString(), oldOrder.getOrderNumber());
        this.addOrder(newOrder);
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
    public void saveWork() throws FilePersistenceException, OrderNotFoundException {
        PrintWriter out;
        orderFileList.clearList();  
        for (String date : orders.keySet()) {
            String tempFileName = "Orders_" + date + ".txt";
            try {
                out = new PrintWriter(new FileWriter(tempFileName));
            } catch (IOException e) {
                throw new FilePersistenceException("Could not write orders to file: " + tempFileName);
            }
            orderFileList.addFile(tempFileName);
            for (Order currentOrder : orders.get(date).values()) {
                out.println(currentOrder.getOrderDate() + DELIMITER 
                        + currentOrder.getOrderNumber() + DELIMITER
                        + currentOrder.getCustomerName().replace(',', '$') + DELIMITER
                        + currentOrder.getTaxInfo().getState() + DELIMITER
                        + currentOrder.getTaxInfo().getRate() + DELIMITER
                        + currentOrder.getProductInfo().getType() + DELIMITER
                        + currentOrder.getArea() + DELIMITER
                        + currentOrder.getProductInfo().getCostPerSquareFoot() + DELIMITER
                        + currentOrder.getProductInfo().getLaborCostPerSquareFoot() + DELIMITER
                        + currentOrder.getMaterialCost() + DELIMITER
                        + currentOrder.getLaborCost() + DELIMITER
                        + currentOrder.getTax() + DELIMITER
                        + currentOrder.getTotal());
                out.flush();
            }
            out.close();
        }
        orders.clear();
        loadOrders();
    }

    
    
    private void loadOrders() throws FilePersistenceException {
        if (!orderFileList.getAllFiles().isEmpty()) {
            Scanner scanner;
            List<String> fileNames = orderFileList.getAllFiles();
            for (String fileName : fileNames) {
                try {
                    scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
                } catch (FileNotFoundException e) {
                    throw new FilePersistenceException("Could not load from order file: " + fileName, e);
                }
                String currentLine;
                String[] currentTokens;

                while(scanner.hasNextLine()) {
                    currentLine = scanner.nextLine();
                    currentTokens = currentLine.split(DELIMITER);
                    Order currentOrder = new Order();
                    Tax tax = new Tax();
                    Product product = new Product();
                    currentOrder.setOrderDate(LocalDate.parse(currentTokens[0]));
                    currentOrder.setOrderNumber(Integer.parseInt(currentTokens[1]));
                    currentOrder.setCustomerName(currentTokens[2].replace('$', ','));
                    tax.setState(currentTokens[3]);
                    tax.setRate(new BigDecimal(currentTokens[4]));
                    currentOrder.setTaxInfo(tax);
                    product.setType(currentTokens[5]);
                    currentOrder.setArea(Double.parseDouble(currentTokens[6]));
                    product.setCostPerSquareFoot(new BigDecimal(currentTokens[7]));
                    product.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[8]));
                    currentOrder.setProductInfo(product);
                    currentOrder.setMaterialCost(new BigDecimal(currentTokens[9]));
                    currentOrder.setLaborCost(new BigDecimal(currentTokens[10]));
                    currentOrder.setTax(new BigDecimal(currentTokens[11]));
                    currentOrder.setTotal(new BigDecimal(currentTokens[12]));
                    this.addOrder(currentOrder);
                }
                scanner.close();
            }
        }
    }
    
    
    
    private Order copyOrder(Order oldOrder) {
        Order tempOrder = new Order();
        tempOrder.setOrderDate(oldOrder.getOrderDate());
        tempOrder.setOrderNumber(oldOrder.getOrderNumber());
        tempOrder.setCustomerName(oldOrder.getCustomerName());
        tempOrder.setTaxInfo(oldOrder.getTaxInfo());
        tempOrder.setProductInfo(oldOrder.getProductInfo());
        tempOrder.setArea(oldOrder.getArea());
        tempOrder.setMaterialCost(oldOrder.getMaterialCost());
        tempOrder.setLaborCost(oldOrder.getLaborCost());
        tempOrder.setTax(oldOrder.getTax());
        tempOrder.setTotal(oldOrder.getTotal());
        return tempOrder;
    }
    
}
