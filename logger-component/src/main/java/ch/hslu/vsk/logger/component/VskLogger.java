/**
 * VskLogger.java
 * Created on 05.10.2020
 *
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.common.Message;

/**
 * Code of Class VskLogger.
 *
 * @author Tobias Heller
 */
//TODO (hellerto): Implements Logger interface
public class VskLogger {
    
    private static VskLogger logger = null;
    
    protected static VskLogger getInstance(){
       if(logger==null){
           logger = new VskLogger();
       }
       return logger;
    }    
    
    private VskLogger(){
        
    }
    
    public void fatal(String message) {
        fatal(message, null);
    }

    public void fatal(String message, Exception exception) {
        createMessage("fatal", message, exception);
    }

    public void error(String message) {
        createMessage("error", message);
    }

    public void warn(String message) {
        createMessage("warn", message);
    }

    public void info(String message) {
        createMessage("info", message);
    }

    public void debug(String message) {
        createMessage("debug", message);

    }

    public void trace(String message) {
        createMessage("trace", message);
    }

    private void createMessage(String logLevel, String content) {
        //TODO hellerto: Check for current Level
        Message message = new Message(logLevel, content);
        //TODO hellerto: Prepare for TCP/IP Transport
        System.out.println(message);
    }

    private void createMessage(String logLevel, String content, Exception exception) {
        String formattedMessage = String.format("%s - %s - %s", content, exception.getLocalizedMessage(), exception.getStackTrace().toString());
        createMessage(logLevel, formattedMessage);
    }
}
