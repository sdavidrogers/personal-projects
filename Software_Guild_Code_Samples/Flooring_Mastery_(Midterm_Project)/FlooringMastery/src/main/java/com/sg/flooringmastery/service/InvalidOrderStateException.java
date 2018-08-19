package com.sg.flooringmastery.service;


public class InvalidOrderStateException extends Exception {
    
    public InvalidOrderStateException(String message) {
        super(message);
    }
    
    public InvalidOrderStateException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
