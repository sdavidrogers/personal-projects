package com.sg.flooringmastery.ui;

import java.math.BigDecimal;
import java.util.ArrayList;


public interface UserIO {
    
    void print(String msg);
    
    String readString(String prompt);
    String readString(String prompt, ArrayList<String> values);
    String readStringDate(String prompt);
    
    double readDouble(String prompt);
    double readDouble(String prompt, double min, double max);
    double readDoubleNotNegative(String prompt);
    
    float readFloat(String prompt);
    float readFloat(String prompt, float min, float max);
    
    int readInt(String prompt);
    int readInt(String prompt, int min, int max);
    
    long readLong(String prompt);
    long readLong(String prompt, long min, long max);
    
    BigDecimal readBigDecimal(String prompt);
    BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max);
    
}
