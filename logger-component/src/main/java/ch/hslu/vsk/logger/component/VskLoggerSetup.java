/**
 * VskLoggerSetup.java
 * Created on 05.10.2020
 * 
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
*/
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.api.*;
import java.net.InetAddress;

/**
 * Code of Class VskLoggerSetup.
 * @author Tobias Heller
 */

public class VskLoggerSetup implements LoggerSetup {
    private LogLevel minimumLevel = LogLevel.INFO;
    private String name;
    private InetAddress serverIp;

    /**
     * Creates VskLogger with current settings.
     * @return VskLogger instance
     */
    @Override
    public Logger createLogger(){
        return new VskLogger(minimumLevel, serverIp);
    }

    /**
     * Set minimum log level.
     * @param logLevel The minimum level that will be logged.
     */
    public void setMinimumLevel(final LogLevel logLevel){
        this.minimumLevel = logLevel;
    }

    /**
     * Set logger name to reference the application from where the logging occurs.
     * @param name
     */
    public void setLoggerName(final String name){
        this.name = name;
    }

    /**
     * Set serverIp address of the server the logs are getting sent.
     * @param ip The serverIp address.
     */
    public void setLoggerServer(final InetAddress ip){
        this.serverIp = ip;
    }
}