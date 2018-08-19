package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


public class Order {
    
    
    
    private int orderNumber;
    private LocalDate orderDate;
    private String customerName;
    private double area;
    private Product productInfo;
    private Tax taxInfo;
    private BigDecimal materialCost, laborCost, tax, total;

    
    
    public int getOrderNumber() {
        return orderNumber;
    }

    
    
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    
    
    public LocalDate getOrderDate() {
        return orderDate;
    }

    
    
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    
    
    public String getCustomerName() {
        return customerName;
    }

    
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    
    
    public double getArea() {
        return area;
    }

    
    
    public void setArea(double area) {
        this.area = area;
    }

    
    
    public Product getProductInfo() {
        return productInfo;
    }

    
    
    public void setProductInfo(Product productInfo) {
        this.productInfo = productInfo;
    }

    
    
    public Tax getTaxInfo() {
        return taxInfo;
    }

    
    
    public void setTaxInfo(Tax taxInfo) {
        this.taxInfo = taxInfo;
    }

    
    
    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    
    
    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    
    
    public BigDecimal getLaborCost() {
        return laborCost;
    }

    
    
    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    
    
    public BigDecimal getTax() {
        return tax;
    }

    
    
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    
    
    public BigDecimal getTotal() {
        return total;
    }

    
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.orderNumber;
        hash = 59 * hash + Objects.hashCode(this.orderDate);
        hash = 59 * hash + Objects.hashCode(this.customerName);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.area) ^ (Double.doubleToLongBits(this.area) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.productInfo);
        hash = 59 * hash + Objects.hashCode(this.taxInfo);
        hash = 59 * hash + Objects.hashCode(this.materialCost);
        hash = 59 * hash + Objects.hashCode(this.laborCost);
        hash = 59 * hash + Objects.hashCode(this.tax);
        hash = 59 * hash + Objects.hashCode(this.total);
        return hash;
    }

    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (Double.doubleToLongBits(this.area) != Double.doubleToLongBits(other.area)) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.productInfo, other.productInfo)) {
            return false;
        }
        if (!Objects.equals(this.taxInfo, other.taxInfo)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "Order{" + "orderNumber=" + orderNumber 
                + ", orderDate=" + orderDate 
                + ", customerName=" + customerName 
                + ", area=" + area 
                + ", productInfo=" + productInfo 
                + ", taxInfo=" + taxInfo 
                + ", materialCost=" + materialCost 
                + ", laborCost=" + laborCost 
                + ", tax=" + tax 
                + ", total=" + total + '}';
    }
    
}
