package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;


public class Tax {
    
    
    
    private String state;
    private BigDecimal rate;

    
    
    public String getState() {
        return state;
    }

    
    
    public void setState(String state) {
        this.state = state;
    }

    
    
    public BigDecimal getRate() {
        return rate;
    }

    
    
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.state);
        hash = 13 * hash + Objects.hashCode(this.rate);
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
        final Tax other = (Tax) obj;
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.rate, other.rate)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "Tax{" + "state=" + state 
                + ", rate=" + rate + '}';
    }
    
}
