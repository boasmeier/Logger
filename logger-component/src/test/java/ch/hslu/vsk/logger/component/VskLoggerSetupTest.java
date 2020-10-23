/**
 * VskLoggerSetup.java
 * Created on 05.10.2020
 * <p>
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.api.LoggerSetup;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Silvan Wenk
 */
class VskLoggerSetupTest {
    private static LoggerSetup setup;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 5050;

    @Test
    void testCreateLogger() throws UnknownHostException {
        setup = new VskLoggerSetup();
        setup.setLoggerName("Test");
        setup.setLoggerServer(HOST, PORT);
        var loggerOne = setup.createLogger();
        var loggerTwo = setup.createLogger();
        assertNotEquals(loggerOne, loggerTwo);
    }

    @Test
    void testCreateLoggerNoServerName() throws UnknownHostException {
        setup = new VskLoggerSetup();
        setup.setLoggerServer(HOST, PORT);
        var message = assertThrows(IllegalStateException.class, setup::createLogger)
                .getMessage();
        assertEquals("Cannot create a logger without a name.", message);

    }

    @Test
    void testCreateLoggerNoServerConnection() {
        setup = new VskLoggerSetup();
        setup.setLoggerName("Test");
        var message = assertThrows(IllegalStateException.class, setup::createLogger)
                .getMessage();
        assertEquals("Cannot create a logger without a server connection.", message);
    }

    @Test
    void testSetMinimumLevel() {
        LoggerSetup setup = new VskLoggerSetup();
        setup.setMinimumLevel(LogLevel.ERROR);
    }

    @Test
    void testSetLoggerName() {
        LoggerSetup setup = new VskLoggerSetup();
        setup.setLoggerName("Test");
    }

    @Test
    void testSetLoggerServer() throws UnknownHostException {
        LoggerSetup setup = new VskLoggerSetup();
        final InetAddress inet = InetAddress.getLoopbackAddress();
        setup.setLoggerServer(HOST, PORT);
    }

}
