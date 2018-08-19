package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FilePersistenceException;
import com.sg.flooringmastery.dao.OrderNotFoundException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.InvalidOrderStateException;
import com.sg.flooringmastery.service.Service;
import com.sg.flooringmastery.ui.View;
import java.util.Map;


public class Controller {
    
    
    
    private final Service service;
    private final View view;
    
    
    
    public Controller(View view, Service service) {
        this.view = view;
        this.service = service;
    }
    
    
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection;
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1: 
                        addOrder();
                        break;
                    case 2:
                        removeOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        listAllOrders();
                        break;
                    case 5:
                        listOrdersByDate();
                        break;
                    case 6:
                        findOrderByNumber();
                        break;
                    case 7:
                        listProductInfo();
                        break;
                    case 8:
                        listTaxInfo();
                        break;
                    case 9:
                        saveWork();
                        break;
                    case 10:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();            
        } catch (FilePersistenceException | OrderNotFoundException | InvalidOrderStateException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    
    
    private void addOrder() throws InvalidOrderStateException, FilePersistenceException {
        try {
            Order order = view.getNewOrderInfo(service.getAllProductInfo(), service.getAllTaxInfo());
            order = service.generateOrder(order);
            boolean confirm = view.confirmAddOrderDetails(order);
            if (confirm) {
                service.addOrder(order);
                view.displayOrderAddedMessage();
            } else {
                view.displayActionCancelled();
            }
        } catch (InvalidOrderStateException | FilePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    
    
    private void removeOrder() throws OrderNotFoundException {
        view.removeOrderBanner();
        String date = view.getDateInput();
        int orderNum = view.getOrderNumberInput();
        try {
            Order order = service.getOrderByNumber(date, orderNum);
            boolean confirm = view.confirmOrderDetails(order);
            if (confirm) {
                service.removeOrder(order);
                view.displayOrderRemoved();
            } else {
                view.displayActionCancelled();
            }
        } catch (OrderNotFoundException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    
    
    private void editOrder() throws InvalidOrderStateException, OrderNotFoundException, FilePersistenceException {
        String date = view.getDateInput();
        int orderNum = view.getOrderNumberInput();
        try {
            Order oldOrder = service.getOrderByNumber(date, orderNum);
            boolean confirm = view.confirmOrderDetails(oldOrder);
            if (confirm) {
                Order newOrder = view.getEditOrderInfo(oldOrder, service.getAllProductInfo(), service.getAllTaxInfo());
                newOrder = service.generateOrder(newOrder);
                confirm = view.confirmOrderDetails(newOrder);
                if (confirm) {
                    service.editOrder(oldOrder, newOrder);
                    view.displayOrderEdited();
                } else {
                    view.displayActionCancelled();
                }
            } else {
                view.displayActionCancelled();
            }
        } catch (InvalidOrderStateException | OrderNotFoundException | FilePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    
    
    private void listAllOrders() {
        view.displayAllOrdersBanner();
        Map<String, Map<Integer, Order>> orders = service.getAllOrders();
        view.displayAllOrders(orders);
    }
    
    
    
    private void listOrdersByDate() throws OrderNotFoundException {
        try {
            String date = view.getDateInput();
            view.displayOrdersByDate(service.getOrdersByDate(date));
        } catch (OrderNotFoundException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    
    
    private void findOrderByNumber() throws OrderNotFoundException {
        String date = view.getDateInput();
        int orderNum = view.getOrderNumberInput();
        try {
            Order order = service.getOrderByNumber(date, orderNum);
            view.displayOrder(order);
        } catch (OrderNotFoundException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    
    
    private void listProductInfo() {
        view.displayProductInfo(service.getAllProductInfo());
    }
    
    
    
    private void listTaxInfo() {
        view.displayTaxInfo(service.getAllTaxInfo());
    }
    
    
    
    private void saveWork() throws FilePersistenceException, OrderNotFoundException {
        try {
            service.saveWork();
            view.workSavedMessage();
        } catch (FilePersistenceException | OrderNotFoundException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    
    
    private void unknownCommand() {
        view.displayUnknownCommandMessage();
    }
    
    
    
    private void exitMessage() {
        view.displayExitMessage();
    }
    
}
