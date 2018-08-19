package com.sg.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class MaxOrderNumberDAOFileImpl implements MaxOrderNumberDAO {
    
    private static final String MAX_ORDER_FILE = "maxOrderNumber.txt";
    private int maxOrderNumber;

    
    
    @Override
    public int getMaxOrderNumber() throws FilePersistenceException {
        loadMaxNumber();
        int temp = maxOrderNumber;
        increment();
        return temp;
    }

    
    
    private void increment() throws FilePersistenceException {
        maxOrderNumber++;
        writeMaxNumber();
    }
    
    
    
    private void loadMaxNumber() throws FilePersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(MAX_ORDER_FILE)));
        } catch (FileNotFoundException e) {
            throw new FilePersistenceException("Could not load file for max order number.", e);
        }
        maxOrderNumber = Integer.parseInt(scanner.nextLine());
        scanner.close();
    }
    
    
    
    private void writeMaxNumber() throws FilePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(MAX_ORDER_FILE));
        } catch (IOException e) {
            throw new FilePersistenceException("Could not write to max number file.", e);
        }  
        out.println(maxOrderNumber);
        out.flush();
        out.close();
    }
       
}
