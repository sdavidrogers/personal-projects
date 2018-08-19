package com.sg.flooringmastery.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;


public interface ProductDAO {
    
    public ArrayList<BigDecimal> getProductPrices(String material);
    
    public Map<String, ArrayList<BigDecimal>> getAllInfo();
    
}
