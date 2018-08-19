package com.sg.flooringmastery.dao;


public class FilePersistenceException extends Exception {
    
    public FilePersistenceException(String message) {
        super(message);
    }
    
    public FilePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
