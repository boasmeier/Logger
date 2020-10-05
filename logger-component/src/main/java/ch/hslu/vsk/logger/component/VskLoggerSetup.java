/**
 * VskLoggerSetup.java
 * Created on 05.10.2020
 * 
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
*/
package ch.hslu.vsk.logger.component;

import java.net.InetAddress;

/**
 * Code of Class VskLoggerSetup.
 * @author Tobias Heller
 */

//TODO (hellerto): Implements LoggerSetup interface

public class VskLoggerSetup {
    private String minimumLevel = "INFO";
    private String name;
    private InetAddress ip;
    
    public VskLogger createLogger(){
        return VskLogger.getInstance();
    }
    
    public void setMinimumLevel(final String logLevel){
        this.minimumLevel = logLevel;
    }
    
    public void setLoggerName(final String name){
        this.name = name;
    }
    
    public void setLoggerServer(final InetAddress ip){
        this.ip = ip;
    }
}