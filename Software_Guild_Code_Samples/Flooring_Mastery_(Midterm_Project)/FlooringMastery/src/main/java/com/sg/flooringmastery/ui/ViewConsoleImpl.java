package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;


public class ViewConsoleImpl implements View {
    
    
    
    private final UserIO io;
    
    
    
    public ViewConsoleImpl(UserIO io) {
        this.io = io;
    }

    
    
    @Override
    public int printMenuAndGetSelection() {
        io.print("\n===== Main Menu =====");
        io.print("Command Options:");
        io.print("1. Add order");
        io.print("2. Remove order");
        io.print("3. Edit order");
        io.print("4. List all orders");
        io.print("5. List orders from date");
        io.print("6. Find order by number");
        io.print("7. List product information");
        io.print("8. List tax information");
        io.print("9. Save work");
        io.print("10. Exit program");
        return io.readInt("Enter # of desired command: ", 1, 10);
    }
    
    
    
    @Override
    public Order getNewOrderInfo(Map<String, ArrayList<BigDecimal>> productInfo, Map<String, BigDecimal> taxInfo) {
        io.print("\n===== Add Order Info =====");
        Order order = new Order();
        Tax tax = new Tax();
        Product product = new Product();
        ArrayList<String> stateVals = new ArrayList<>(taxInfo.keySet());
        ArrayList<String> productTypes = new ArrayList<>(productInfo.keySet());
        order.setOrderDate(LocalDate.parse(io.readStringDate("-- Enter order date --")));
        order.setCustomerName(io.readString("\nEnter customer name: "));
        tax.setState(io.readString("Enter state: ", stateVals));
        order.setTaxInfo(tax);
        product.setType(io.readString("Enter product type: ", productTypes));
        order.setProductInfo(product);
        order.setArea(io.readDoubleNotNegative("Enter flooring area: "));
        io.print("");
        return order;
    }
    
    
    
    @Override
    public Order getEditOrderInfo(Order order, Map<String, ArrayList<BigDecimal>> productInfo, Map<String, BigDecimal> taxInfo) {
        io.print("\n===== Edit Order Info =====");
        ArrayList<String> stateVals = new ArrayList<>(taxInfo.keySet());
        stateVals.add("");
        ArrayList<String> productTypes = new ArrayList<>(productInfo.keySet());
        productTypes.add("");
        Order newOrder = new Order();
        newOrder.setOrderNumber(order.getOrderNumber());
        Tax tax = new Tax();
        Product product = new Product();
        io.print("** Existing values displayed in parentheses **");
        io.print("**     Hit enter to keep existing value     **");
        
        String date = editGetDate(order.getOrderDate().toString());
        newOrder.setOrderDate(LocalDate.parse(date));
        
        String customerName = io.readString("Enter customer name (" + order.getCustomerName() + "): ");
        if (customerName.length() >= 1) {
            newOrder.setCustomerName(customerName);
        } else {
            newOrder.setCustomerName(order.getCustomerName());
        }
        
        String state = io.readString("Enter state (" + order.getTaxInfo().getState() + "): ", stateVals);
        if (state.length() >= 1) {
            tax.setState(state);
        } else {
            tax.setState(order.getTaxInfo().getState());
        }
        newOrder.setTaxInfo(tax);
        
        String type = io.readString("Enter product type (" + order.getProductInfo().getType() + "): ", productTypes);
        if (type.length() >= 1) {
            product.setType(type);
        } else {
            product.setType(order.getProductInfo().getType());
        }
        newOrder.setProductInfo(product);
        
        String area = editGetArea(order.getArea());
        if (area.length() >= 1) {
            newOrder.setArea(Double.parseDouble(area));
        } else {
            newOrder.setArea(order.getArea());
        }
        
        io.print("");
        return newOrder;
    }

    
    
    @Override
    public boolean confirmOrderDetails(Order order) {
        ArrayList<String> yesNo = new ArrayList<>();
        yesNo.add("Y");
        yesNo.add("N");
        io.print(formatOrder(order));
        String check = io.readString("\nConfirm order details (Y/N): ", yesNo);
        io.print("");
        return check.equals("Y");
    }
    
    
    
    @Override
    public boolean confirmAddOrderDetails(Order order) {
        ArrayList<String> yesNo = new ArrayList<>();
        yesNo.add("Y");
        yesNo.add("N");
        io.print(formatOrderNoOrderNumber(order));
        String check = io.readString("\nConfirm order details (Y/N): ", yesNo);
        io.print("");
        return check.equals("Y");
    }

    
    
    @Override
    public void displayOrder(Order order) {
        io.print("\n===== Display Order =====");
        io.print(formatOrder(order));
    }
    
    
    
    @Override
    public void displayOrdersByDateBanner() {
        io.print("\n===== Display Orders By Date =====\n");
    }
    
    
    
    @Override
    public void displayAllOrdersBanner() {
        io.print("\n===== Display All Orders =====\n");
    }
    
    
    
    @Override
    public void removeOrderBanner() {
        io.print("\n===== Remove Order =====");
    }

    
    
    @Override
    public void displayOrderAddedMessage() {
        io.print("Order successfully added.\n");
    }

    
    
    @Override
    public void displayActionCancelled() {
        io.print("Order action cancelled.\n");
    }

    
    
    @Override
    public String getDateInput() {
        return io.readStringDate("\n-- Enter order date -- ");
    }

    
    
    @Override
    public int getOrderNumberInput() {
        return io.readInt("Enter order number: ");
    }

    
    
    @Override
    public void displayOrderRemoved() {
        io.print("Order successfully removed.\n");
    }

    
    
    @Override
    public void displayOrderEdited() {
        io.print("Order successfully edited.\n");
    }
    
    
    
    @Override
    public void displayAllOrders(Map<String, Map<Integer, Order>> orders) {
        for (String date : orders.keySet()) {
            this.displayOrdersByDate(orders.get(date));
        }
    }

    
    
    @Override
    public void displayOrdersByDate(Map<Integer, Order> orders) {
        for (Integer key : orders.keySet()) {
            io.print(formatOrder(orders.get(key)));
        }
    }

    
    
    @Override
    public void displayProductInfo(Map<String, ArrayList<BigDecimal>> productInfo) {
        io.print("\n===== Product Information =====\n");
        for (String key : productInfo.keySet()) {
            String addedVals = "";
            ArrayList<BigDecimal> currentVals = productInfo.get(key);
            for (BigDecimal val : currentVals) {
                addedVals += " -- " + val;
            }
            io.print(key + addedVals);
        }
        io.print("");
    }

    
    
    @Override
    public void displayTaxInfo(Map<String, BigDecimal> taxInfo) {
        io.print("\n===== Tax Information =====\n");
        for (String key : taxInfo.keySet()) {
            io.print(key + " -- " + taxInfo.get(key));
        }
        io.print("");
    }

    
    
    @Override
    public void workSavedMessage() {
        io.print("\nWork successfully saved to file.");
    }

    
    
    @Override
    public void displayUnknownCommandMessage() {
        io.print("Invalid command entry.\n");
    }

    
    
    @Override
    public void displayExitMessage() {
        io.print("\nProgram session terminated.");
    }
    
    
    
    @Override
    public void displayErrorMessage(String message) {
        io.print(message);
    }
    
    
    
    private String formatOrder(Order order) {
        return "\nOrder date: " + order.getOrderDate() 
                + "\nOrder number: " + order.getOrderNumber()
                + "\nCustomer name: " + order.getCustomerName()
                + "\nState: " + order.getTaxInfo().getState()
                + "\nProduct type: " + order.getProductInfo().getType()
                + "\nArea: " + order.getArea()
                + "\nTax rate: " + order.getTaxInfo().getRate() + "%"
                + "\nMaterial cost per square foot: " + NumberFormat.getCurrencyInstance().format(order.getProductInfo().getCostPerSquareFoot())
                + "\nLabor cost per square foot: " + NumberFormat.getCurrencyInstance().format(order.getProductInfo().getLaborCostPerSquareFoot())
                + "\nMaterial cost: " + NumberFormat.getCurrencyInstance().format(order.getMaterialCost())
                + "\nLabor cost: " + NumberFormat.getCurrencyInstance().format(order.getLaborCost())
                + "\nTax: " + NumberFormat.getCurrencyInstance().format(order.getTax())
                + "\nTotal: " + NumberFormat.getCurrencyInstance().format(order.getTotal());
    }
    
    
    
    private String formatOrderNoOrderNumber(Order order) {
        return "\nOrder date: " + order.getOrderDate()
                + "\nCustomer name: " + order.getCustomerName()
                + "\nState: " + order.getTaxInfo().getState()
                + "\nProduct type: " + order.getProductInfo().getType()
                + "\nArea: " + order.getArea()
                + "\nTax rate: " + order.getTaxInfo().getRate() + "%"
                + "\nMaterial cost per square foot: " + NumberFormat.getCurrencyInstance().format(order.getProductInfo().getCostPerSquareFoot())
                + "\nLabor cost per square foot: " + NumberFormat.getCurrencyInstance().format(order.getProductInfo().getLaborCostPerSquareFoot())
                + "\nMaterial cost: " + NumberFormat.getCurrencyInstance().format(order.getMaterialCost())
                + "\nLabor cost: " + NumberFormat.getCurrencyInstance().format(order.getLaborCost())
                + "\nTax: " + NumberFormat.getCurrencyInstance().format(order.getTax())
                + "\nTotal: " + NumberFormat.getCurrencyInstance().format(order.getTotal());
    }
    
    
    // Specialized library for edit process to avoid tight coupling with UserIO
    
    
    private String editGetArea(double area) {
        String returnVal;
        do {
            returnVal = io.readString("Enter flooring area (" + area + "): ");
            if (returnVal.length() == 0) {
                break;
            } else {
                try {
                    double temp = Double.parseDouble(returnVal);
                    if (temp < 1) {
                        io.print("Invalid entry. Enter a positive number.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    io.print("Invalid entry. Enter a decimal value number (0.00).");
                }
            }
        } while (true);
        return returnVal;
    }
    
    
    
    private String editGetDate(String date) {
        String[] dateParts = date.split("-");
        io.print("-- Enter order date --");
        dateParts[0] = editGetDateEntry("year", dateParts[0], 1900, 3000, 4);
        dateParts[1] = editGetDateEntry("month", dateParts[1], 1, 12, 2);
        dateParts[2] = editGetDateEntry("day", dateParts[2], 1, 31, 2);
        return dateParts[0] + "-" + dateParts[1] + "-" + dateParts[2];
    }
    
    
    
    private String editGetDateEntry(String type, String currentVal, int min, int max, int length) {
        String returnVal;
        do {
            String temp = io.readString("Enter " + type + " (" + currentVal + "): ");
            if (temp.length() == 0) {
                returnVal = currentVal;
                break;
            } else {
                try {
                    int tempInt = Integer.parseInt(temp);
                    if (tempInt > min && tempInt < max) {
                        if (temp.length() > length) {
                            temp = temp.substring(temp.length() - length);
                        } else if (temp.length() < length) {
                            temp = "0" + temp;
                        }
                        returnVal = temp;
                        break;
                    } else {
                        io.print("Invalid entry. Enter a number between " + min + " and " + max + ".");
                    }
                } catch (NumberFormatException e) {
                    io.print("Invalid entry. Enter a whole number.");
                }
            }
        } while (true);
        return returnVal;
    }
    
}
