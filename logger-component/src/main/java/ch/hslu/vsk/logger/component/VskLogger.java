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
import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.logger.common.MessageHelper;
import java.net.InetAddress;

/**
 * Code of Class VskLogger.
 *
 * @author Tobias Heller, Silvan Wenk
 */
public final class VskLogger implements Logger {

    private LogLevel minLevel;
    private String name;
    private Connection serverConnection;

    VskLogger(final LogLevel minLevel, final String name, final Connection conn) {
        this.minLevel = minLevel;
        this.name = name;
        this.serverConnection = conn;
    }

    /**
     * Sets the minimum Log Level of Messages sent.
     *
     * @param minLevel Minimum LogLevel.
     */
    @Override
    public void setMinimumLevel(final LogLevel minLevel) {
        this.minLevel = minLevel;
    }

    /**
     * Gets the minimum log level of Messages sent.
     * @return
     */
    @Override
    public LogLevel getMinimumLevel() {
        return this.minLevel;
    }

    /**
     * Sends a Message of LogLevel.TRACE if minLevel is equal or lower than
     * TRACE.
     *
     * @param message Message to send.
     */
    @Override
    public void trace(final String message) {
        createMessage(LogLevel.TRACE, message);
    }

    /**
     * Sends a Message of LogLevel.DEBUG if minLevel is equal or lower than
     * DEBUG.
     *
     * @param message Message to send.
     */
    @Override
    public void debug(final String message) {
        createMessage(LogLevel.DEBUG, message);
    }

    /**
     * Sends a Message of LogLevel.INFO if minLevel is equal or lower than INFO.
     *
     * @param message Message to send.
     */
    @Override
    public void info(final String message) {
        createMessage(LogLevel.INFO, message);
    }

    /**
     * Sends a Message of LogLevel.WARN if minLevel is equal or lower than WARN.
     *
     * @param message Message to send.
     */
    @Override
    public void warn(final String message) {
        createMessage(LogLevel.WARN, message);
    }

    /**
     * Sends a Message of LogLevel.FATAL if minLevel is equal or lower than
     * FATAL.
     *
     * @param message Message to send.
     */
    @Override
    public void fatal(final String message, final Throwable exception) {
        createMessage(LogLevel.FATAL, message, exception);
    }

    /**
     * Sends a Message of LogLevel.ERROR if minLevel is equal or lower than
     * ERROR.
     *
     * @param message Message to send.
     */
    @Override
    public void error(final String message, final Throwable exception) {
        createMessage(LogLevel.ERROR, message, exception);
    }

    private void createMessage(final LogLevel logLevel, final String message) {
        if (shouldBeSent(logLevel)) {
            LogMessage msg = new LogMessage(this.name, logLevel, message);
            serverConnection.send(msg);
        }
    }

    private void createMessage(final LogLevel logLevel, final String content, final Throwable ex) {
        createMessage(logLevel, MessageHelper.ExceptionMessageToString(content, ex));
    }

    private boolean shouldBeSent(final LogLevel logLevel) {
        return logLevel.compareTo(minLevel) >= 0;
    }
}
