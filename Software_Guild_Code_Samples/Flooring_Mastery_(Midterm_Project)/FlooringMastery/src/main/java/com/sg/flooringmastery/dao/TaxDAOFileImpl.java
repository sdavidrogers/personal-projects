package com.sg.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class TaxDAOFileImpl implements TaxDAO {
    
    private static final String TAX_INFO_FILE = "taxInfo.txt";
    public static final String DELIMITER = ",";
    private final Map<String, BigDecimal> taxInfo = new HashMap<>();
    
    public TaxDAOFileImpl() throws FilePersistenceException {
        loadTaxInfo();
    }

    @Override
    public BigDecimal getStateTaxRate(String state) {
        return taxInfo.get(state);
    }

    @Override
    public Map<String, BigDecimal> getAllInfo() {
        return taxInfo;
    }
    
    private void loadTaxInfo() throws FilePersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(TAX_INFO_FILE)));
        } catch (FileNotFoundException e) {
            throw new FilePersistenceException("Could not load tax information from file.", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            taxInfo.put(currentTokens[0], new BigDecimal(currentTokens[1]));
       }    
        scanner.close();
    }
    
}
