/**
 * VskLogger.java
 * Created on 05.10.2020
 *
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.api.Logger;
import ch.hslu.vsk.logger.common.Message;

/**
 * Code of Class VskLogger.
 *
 * @author Tobias Heller
 */
//TODO (hellerto): Implements Logger interface
public class VskLogger {
    
    private static VskLogger logger = null;
    
    protected static Logger getInstance(){
       if(logger == null){
           logger = new VskLogger();
       }
       return (Logger) logger;
    }    
    
    private VskLogger(){
        
    }
    
    public void fatal(String message) {
        fatal(message, null);
    }

    public void fatal(String message, Exception ex) {
        createMessage(LogLevel.FATAL, message, ex);
    }

    public void error(String message) {
        createMessage(LogLevel.ERROR, message);
    }

    public void warn(String message) {
        createMessage(LogLevel.WARN, message);
    }

    public void info(String message) {
        createMessage(LogLevel.INFO, message);
    }

    public void debug(String message) {
        createMessage(LogLevel.DEBUG, message);

    }

    public void trace(String message) {
        createMessage(LogLevel.TRACE, message);
    }

    private void createMessage(LogLevel logLevel, String content) {
        //TODO hellerto: Check for current Level
        Message message = new Message(logLevel, content);
        //TODO hellerto: Prepare for TCP/IP Transport
        System.out.println(message);
    }

    private void createMessage(LogLevel logLevel, String content, Exception ex) {
        String formattedMessage = String.format("%s - %s - %s", content, ex.getLocalizedMessage(), ex.getStackTrace().toString());
        createMessage(logLevel, formattedMessage);
    }
}
