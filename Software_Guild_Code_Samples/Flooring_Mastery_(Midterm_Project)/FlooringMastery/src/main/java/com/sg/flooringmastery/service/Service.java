package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FilePersistenceException;
import com.sg.flooringmastery.dao.MaxOrderNumberDAO;
import com.sg.flooringmastery.dao.MaxOrderNumberDAOFileImpl;
import com.sg.flooringmastery.dao.OrderDAO;
import com.sg.flooringmastery.dao.OrderNotFoundException;
import com.sg.flooringmastery.dao.ProductDAO;
import com.sg.flooringmastery.dao.ProductDAOFileImpl;
import com.sg.flooringmastery.dao.TaxDAO;
import com.sg.flooringmastery.dao.TaxDAOFileImpl;
import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;


public class Service {
    
    private final OrderDAO orderDAO;
    private final MaxOrderNumberDAO maxOrderNumberDao;
    private final ProductDAO productDao;
    private final TaxDAO taxDao;
    
    
    
    public Service(OrderDAO orderDAO) throws FilePersistenceException {
        this.orderDAO = orderDAO;
        maxOrderNumberDao = new MaxOrderNumberDAOFileImpl();
        productDao = new ProductDAOFileImpl();
        taxDao = new TaxDAOFileImpl();
    }
    
    
    
    public Order generateOrder(Order order) throws InvalidOrderStateException, FilePersistenceException {
        Order currentOrder = order;
        validateOrderData(currentOrder);
        currentOrder.getTaxInfo().setRate(taxDao.getStateTaxRate(currentOrder.getTaxInfo().getState()));
        ArrayList<BigDecimal> productPrices = productDao.getProductPrices(currentOrder.getProductInfo().getType());
        currentOrder.getProductInfo().setCostPerSquareFoot(productPrices.get(0));
        currentOrder.getProductInfo().setLaborCostPerSquareFoot(productPrices.get(1));
        currentOrder.setMaterialCost(currentOrder.getProductInfo().getCostPerSquareFoot()
                .multiply(BigDecimal.valueOf(currentOrder.getArea())));
        currentOrder.setLaborCost(currentOrder.getProductInfo().getLaborCostPerSquareFoot()
                .multiply(BigDecimal.valueOf(currentOrder.getArea())));
        currentOrder.setTax((currentOrder.getMaterialCost().divide(currentOrder.getTaxInfo().getRate(), 2)
                .add(currentOrder.getLaborCost().divide(currentOrder.getTaxInfo().getRate(), 2))));
        currentOrder.setTotal(currentOrder.getMaterialCost()
                .add(currentOrder.getLaborCost()
                        .add(currentOrder.getTax())));
        return currentOrder;
    }
    
    
    
    public Order addOrder(Order order) throws FilePersistenceException {
        Order currentOrder = order;
        currentOrder.setOrderNumber(maxOrderNumberDao.getMaxOrderNumber());
        return orderDAO.addOrder(currentOrder);
    }
    
    
    
    public Order getOrderByNumber(String date, int orderNumber) throws OrderNotFoundException {
        return orderDAO.getOrderByNumber(date, orderNumber);
    }
    
    
    
    public Map<Integer, Order> getOrdersByDate(String date) throws OrderNotFoundException {
        return orderDAO.getOrdersByDate(date);
    }
    
    
    
    public Map<String, Map<Integer, Order>> getAllOrders() {
        return orderDAO.getAllOrders();
    }
    
    
    
    public Order removeOrder(Order order) throws OrderNotFoundException {
        return orderDAO.removeOrder(order.getOrderDate().toString(), order.getOrderNumber());
    }
    
    
    
    public Order editOrder(Order oldOrder, Order newOrder) throws OrderNotFoundException {
        return orderDAO.editOrder(oldOrder, newOrder);
    }
    
    
    
    public Map<String, ArrayList<BigDecimal>> getAllProductInfo() {
        return productDao.getAllInfo();
    }
    
    
    
    public Map<String, BigDecimal> getAllTaxInfo() {
        return taxDao.getAllInfo();
    }
    
    
    
    public void saveWork() throws FilePersistenceException, OrderNotFoundException {
        orderDAO.saveWork();
    }
    
    
    
    private void validateOrderData(Order order) throws InvalidOrderStateException {
        Order currentOrder = order;
        if (currentOrder.getCustomerName() == null ||
                currentOrder.getArea() == 0 ||
                currentOrder.getProductInfo().getType() == null ||
                currentOrder.getTaxInfo().getState() == null) {
            throw new InvalidOrderStateException("Incomplete order information. Order could not be added.");
        }
    }
    
}
