package com.sg.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ProductDAOFileImpl implements ProductDAO {
    
    
    
    private static final String PRODUCT_INFO_FILE = "productInfo.txt";
    public static final String DELIMITER = ",";
    private final Map<String, ArrayList<BigDecimal>> productInfo = new HashMap<>();
    
    
    
    public ProductDAOFileImpl() throws FilePersistenceException {
        loadProductInfo();
    }

    
    
    @Override
    public ArrayList<BigDecimal> getProductPrices(String material) {
        return productInfo.get(material);
    }

    
    
    @Override
    public Map<String, ArrayList<BigDecimal>> getAllInfo() {
        return productInfo;
    }
    
    
    
    private void loadProductInfo() throws FilePersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_INFO_FILE)));
        } catch (FileNotFoundException e) {
            throw new FilePersistenceException("Could not load product information from file.", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            ArrayList<BigDecimal> temp = new ArrayList<>();
            temp.add(new BigDecimal(currentTokens[1]));
            temp.add(new BigDecimal(currentTokens[2]));
            productInfo.put(currentTokens[0], temp);
       }    
        scanner.close();
    }
    
}
