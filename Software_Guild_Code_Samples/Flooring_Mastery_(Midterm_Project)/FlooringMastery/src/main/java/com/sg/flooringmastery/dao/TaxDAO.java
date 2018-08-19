package com.sg.flooringmastery.dao;

import java.math.BigDecimal;
import java.util.Map;


public interface TaxDAO {
    
    public BigDecimal getStateTaxRate(String state);
    
    public Map<String, BigDecimal> getAllInfo();
    
}
