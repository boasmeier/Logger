/**
 * VskLoggerSetup.java
 * Created on 05.10.2020
 * <p>
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.api.*;
import java.net.InetAddress;

/**
 * Code of Class VskLoggerSetup.
 * @author Silvan Wenk
 */

public class VskLoggerSetup implements LoggerSetup {
    private LogLevel minimumLevel = LogLevel.INFO;
    private String name;
    private InetAddress serverIp;
    private NetworkService networkService;

    public VskLoggerSetup() {
        this(new NetworkService());
    }
    
    public VskLoggerSetup(NetworkService networkService) {
        this.networkService = networkService;
    }

    /**
     * Creates VskLogger with current settings. It's required to first set the name,
     * the minimum log level and the server ip.
     * @return VskLogger instance
     */
    @Override
    public Logger createLogger() {
        if (this.name == null || this.name.equals("")) {
            throw new IllegalStateException("Cannot create a logger without a name.");
        } else if (this.serverIp == null) {
            throw new IllegalStateException("Cannot create a logger without a server IP Address.");
        } else if (!this.networkService.ipAddressIsReachable(this.serverIp)) {
            throw new IllegalStateException(String.format("IP address (%s) unreachable.", this.serverIp.getHostAddress()));
        }
        return new VskLogger(this.minimumLevel, this.name, this.serverIp);
    }

    /**
     * Set minimum log level.
     * @param minLevel The configured minimum level that will be logged. Lower levels than this won't be logged.
     * e.g. if set to INFO: messages of type DEBUG and TRACE won't be logged.
     */
    public void setMinimumLevel(final LogLevel minLevel) {
        this.minimumLevel = minLevel;
    }

    /**
     * Set logger name to reference the application from where the logging occurs.
     * @param name The name of the logger. This will be written to the logging server together with the log message.
     */
    public void setLoggerName(final String name) {
        this.name = name;
    }

    /**
     * Set serverIp address of the server the logs are getting sent.
     * @param ip The configured server IP address.
     */
    public void setLoggerServer(final InetAddress ip) {
        this.serverIp = ip;
    }
}