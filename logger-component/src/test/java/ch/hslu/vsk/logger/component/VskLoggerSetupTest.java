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

import static org.junit.jupiter.api.Assertions.*;

class VskLoggerSetupTest {
    private static LoggerSetup setup;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 5050;

    @Test
    void testCreateLogger() {
        setup = new VskLoggerSetup();
        setup.setLoggerName("Test");
        setup.setServerIP(HOST);
        setup.setServerPort(PORT);
        var loggerOne = setup.createLogger();
        var loggerTwo = setup.createLogger();
        assertNotEquals(loggerOne, loggerTwo);
    }

    @Test
    void testCreateLoggerNoServerName() {
        setup = new VskLoggerSetup();
        setup.setServerIP(HOST);
        setup.setServerPort(PORT);
        var message = assertThrows(IllegalStateException.class, setup::createLogger)
                .getMessage();
        assertEquals("Cannot create a logger without a name.", message);
    }

    @Test
    void testCreateLoggerNoIpAddress() {
        setup = new VskLoggerSetup();
        setup.setLoggerName("Test");
        setup.setServerPort(PORT);
        var message = assertThrows(IllegalStateException.class, setup::createLogger)
                .getMessage();
        assertEquals("Cannot create a logger without a server ip address.", message);
    }

    @Test
    void testCreateLoggerNoPortNumber() {
        setup = new VskLoggerSetup();
        setup.setLoggerName("Test");
        setup.setServerIP(HOST);
        var message = assertThrows(IllegalStateException.class, setup::createLogger)
                .getMessage();
        assertEquals("Cannot create a logger with an invalid port number.", message);
    }

    @Test
    void testCreateLoggerPortNumberOutOfRange() {
        setup = new VskLoggerSetup();
        setup.setLoggerName("Test");
        setup.setServerIP(HOST);
        setup.setServerPort(Integer.MAX_VALUE);
        var message = assertThrows(IllegalStateException.class, setup::createLogger)
                .getMessage();
        assertEquals("Cannot create a logger with an invalid port number.", message);
    }

    @Test
    void testCreateLoggerPortNumberNegative() {
        setup = new VskLoggerSetup();
        setup.setLoggerName("Test");
        setup.setServerIP(HOST);
        setup.setServerPort(-1);
        var message = assertThrows(IllegalStateException.class, setup::createLogger)
                .getMessage();
        assertEquals("Cannot create a logger with an invalid port number.", message);
    }

    @Test
    void testSetMinimumLevel() {
        setup = new VskLoggerSetup();
        var expected = LogLevel.WARN;
        setup.setMinimumLevel(expected);
        var actual = setup.getMinimumLevel();
        assertEquals(expected, actual);
    }

    @Test
    void testSetLoggerName() {
        setup = new VskLoggerSetup();
        var expected = "Test";
        setup.setLoggerName(expected);
        var actual = setup.getLoggerName();
        assertEquals(expected, actual);
    }

    @Test
    void testSetLoggerIpAddress() {
        setup = new VskLoggerSetup();
        var expected = HOST;
        setup.setServerIP(expected);
        var actual = setup.getServerIP();
        assertEquals(expected, actual);
    }

    @Test
    void testSetLoggerPort() {
        setup = new VskLoggerSetup();
        var expected = PORT;
        setup.setServerPort(expected);
        var actual = setup.getServerPort();
        assertEquals(expected, actual);
    }
}
