/**
 * VskLoggerSetup.java
 * Created on 05.10.2020
 *
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
*/
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.api.*;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Code of Class VskLoggerSetup.
 * @author Silvan Wenk
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
        if (this.name == null || this.name.equals("")) {
            throw new IllegalStateException("Cannot create a logger without a name.");
        } else if (this.serverIp == null) {
            throw new IllegalStateException("Cannot create a logger without a server IP Address.");
        } else if (!this.ipAddressIsReachable(this.serverIp)) {
            throw new IllegalStateException(String.format("IP address (%s) unreachable.", this.serverIp.getHostAddress()));
        }
        return new VskLogger(this.minimumLevel, this.name, this.serverIp);
    }

    /**
     * Set minimum log level.
     * @param minLevel The configured minimum level that will be logged.
     */
    public void setMinimumLevel(final LogLevel minLevel){
        this.minimumLevel = minLevel;
    }

    /**
     * Set logger name to reference the application from where the logging occurs.
     * @param name The configured name of the logger.
     */
    public void setLoggerName(final String name){
        this.name = name;
    }

    /**
     * Set serverIp address of the server the logs are getting sent.
     * @param ip The configured server IP address.
     */
    public void setLoggerServer(final InetAddress ip){
        this.serverIp = ip;
    }

    /**
     * Returns true if the given InetAddress is reachable with ICMP.
     * @param address The given InetAddress.
     * @return true or false
     */
    private boolean ipAddressIsReachable(InetAddress address) {
        int returnVal = 0;
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec(String.format("ping -n 1 %s", address.getHostName()));
            returnVal= p1.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return (returnVal == 0);
    }
}