package com.sg.flooringmastery.advice;

import com.sg.flooringmastery.dao.AuditDAO;
import com.sg.flooringmastery.dao.FilePersistenceException;
import org.aspectj.lang.JoinPoint;


public class AuditLogAdvice {
    
    AuditDAO log;
    
    public AuditLogAdvice(AuditDAO log) {
        this.log = log;
    }   
    
    public void createExceptionLogEntry(JoinPoint jp, Exception ex) 
            throws FilePersistenceException {  
        Object[] args = jp.getArgs();
        String logEntry = ex.getClass().getSimpleName();
        if (args.length >= 1) {
            logEntry += " :: Arguments";
        }
        for (Object currentArg : args) {
            logEntry += currentArg;
        }
        try {
            log.writeLogEntry(logEntry);
        } catch (FilePersistenceException e) {
            System.err.println(
               "ERROR: Could not create exception-thrown entry in LoggingAdvice.");
        }
    }
    
    public void createLogEntry(JoinPoint jp) throws FilePersistenceException {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName();
        if (args.length >= 1) {
            auditEntry += " :: Arguments";
        }
        for (Object currentArg : args) {
            auditEntry += " : " + currentArg;
        }
        try {
            log.writeLogEntry(auditEntry);
        } catch (FilePersistenceException e) {
            System.err.println(
               "ERROR: Could not create method-call entry in LoggingAdvice.");
        }
    }
    
}
