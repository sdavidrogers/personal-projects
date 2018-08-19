package com.sg.flooringmastery.dao;

import java.util.List;


public interface OrderFileListDAO {
    
    public void addFile(String fileName) throws FilePersistenceException;
    
    public List<String> getAllFiles() throws FilePersistenceException;
    
    public void clearList() throws FilePersistenceException;
    
}
