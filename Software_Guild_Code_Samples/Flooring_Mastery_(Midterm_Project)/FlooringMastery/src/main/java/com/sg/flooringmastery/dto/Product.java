package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;


public class Product {
    
    
    
    private String type;
    private BigDecimal costPerSquareFoot, laborCostPerSquareFoot;

    
    
    public String getType() {
        return type;
    }

    
    
    public void setType(String type) {
        this.type = type;
    }

    
    
    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    
    
    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    
    
    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    
    
    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 37 * hash + Objects.hashCode(this.laborCostPerSquareFoot);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPerSquareFoot, other.laborCostPerSquareFoot)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "Product{" + "type=" + type 
                + ", costPerSquareFoot=" + costPerSquareFoot 
                + ", laborCostPerSquareFoot=" + laborCostPerSquareFoot + '}';
    }
    
}
