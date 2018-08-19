package com.sg.flooringmastery.dao;


public interface AuditDAO {
    
    public void writeLogEntry(String entry) throws FilePersistenceException;
    
}
