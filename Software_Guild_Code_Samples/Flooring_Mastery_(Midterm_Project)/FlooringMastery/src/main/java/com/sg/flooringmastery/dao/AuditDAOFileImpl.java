package com.sg.flooringmastery.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;


public class AuditDAOFileImpl implements AuditDAO {
    
    public static final String AUDIT_LOG_FILE = "auditLog.txt";
    
    @Override
    public void writeLogEntry(String entry) throws FilePersistenceException {
        
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(AUDIT_LOG_FILE, true));
        } catch (IOException e) {
            throw new FilePersistenceException("ERROR: Could not persist audit information.", e);
        }
        
        LocalDateTime timeStamp = LocalDateTime.now();
        out.println(timeStamp.toString() + " :: " + entry);
        out.flush();
        
    }
    
}
