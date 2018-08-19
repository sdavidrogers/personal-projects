package com.sg.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class OrderFileListDAOFileImpl implements OrderFileListDAO {
    
    
    
    private static final String ORDER_FILE_LIST = "orderFileList.txt";
    private final List<String> fileList = new ArrayList<>();

    
    
    @Override
    public void addFile(String fileName) throws FilePersistenceException {
        loadFileList();
        fileList.add(fileName);
        writeFileList();
    }

    
    
    @Override
    public List<String> getAllFiles() throws FilePersistenceException {
        loadFileList();
        return fileList;
    }
    
    
    
    @Override
    public void clearList() throws FilePersistenceException {
        loadFileList();
        fileList.clear();
        writeFileList();
    }
    
    
    
    private void writeFileList() throws FilePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ORDER_FILE_LIST));
        } catch (IOException e) {
            throw new FilePersistenceException("Could not save order file list.", e);
        }
        for (String file : fileList) {
            out.println(file);
            out.flush();
        }
        out.close();
        fileList.clear();
    }
    
    
    
    private void loadFileList() throws FilePersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(ORDER_FILE_LIST)));
        } catch (FileNotFoundException e) {
            throw new FilePersistenceException("Could not load order file list to memory.", e);
        }
        while (sc.hasNextLine()) {
            fileList.add(sc.nextLine());
        }
        sc.close();
    }
    
}
