/**
 * VskLoggerSetup.java
 * Created on 05.10.2020
 * <p>
 * Copyright(c) 2020 Tobias Heller. This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.api.*;

public final class VskLoggerSetup implements LoggerSetup {

    private LogLevel minimumLevel = LogLevel.INFO;
    private String name;
    private String ipAddress;
    private int port = 0;

    public VskLoggerSetup() {
    }

    /**
     * Creates VskLogger with current settings. It's required to first set the name, the minimum log level, the server
     * ip and port number.
     *
     * @return VskLogger instance
     */
    @Override
    public final Logger createLogger() {
        if (this.name == null || this.name.equals("")) {
            throw new IllegalStateException("Cannot create a logger without a name.");
        } else if (this.ipAddress == null || this.ipAddress.equals("")) {
            throw new IllegalStateException("Cannot create a logger without a server ip address.");
        } else if (this.port < 1024 || this.port > 65535) {
            throw new IllegalStateException("Cannot create a logger with an invalid port number.");
        }
        return new VskLogger(this.minimumLevel, this.name, new Connection(this.ipAddress, this.port));
    }

    /**
     * Optionally set the minimum log level. Per default the value is set to INFO.
     *
     * @param minLevel The configured minimum level that will be logged. Lower levels than this won't be logged. e.g. if
     * set to INFO: messages of type DEBUG and TRACE won't be logged.
     */
    @Override
    public final void setMinimumLevel(final LogLevel minLevel) {
        this.minimumLevel = minLevel;
    }

    @Override
    public final LogLevel getMinimumLevel() {
        return this.minimumLevel;
    }

    /**
     * Set logger name to reference the application from where the logging occurs.
     *
     * @param name The name of the logger. This will be written to the logging server together with the log message.
     */
    public final void setLoggerName(final String name) {
        this.name = name;
    }

    @Override
    public final String getLoggerName() {
        return this.name;
    }

    /**
     * Sets the server ip address.
     *
     * @param ip The configured server IP address or hostname.
     */
    @Override
    public final void setServerIP(String ip) {
        this.ipAddress = ip;
    }

    /**
     * Gets the server ip address.
     *
     * @return The configured server IP address or hostname.
     */
    @Override
    public final String getServerIP() {
        return this.ipAddress;
    }

    /**
     * Sets the server port number.
     *
     * @param port The port number on which the connection to the server should be made.
     */
    @Override
    public final void setServerPort(final int port) {
        this.port = port;
    }

    /**
     * Gets the server port number.
     *
     * @return The port number on which the connection to the server should be made.
     */
    @Override
    public final int getServerPort() {
        return this.port;
    }
}
