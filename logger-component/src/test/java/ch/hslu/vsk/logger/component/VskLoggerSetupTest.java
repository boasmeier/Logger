/**
 * VskLoggerSetup.java
 * Created on 05.10.2020
 *
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.api.LoggerSetup;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author Silvan Wenk
 */
class VskLoggerSetupTest {
    @Test
    void testCreateLogger() {
        var setup = new VskLoggerSetup();
        setup.setLoggerName("Test");
        setup.setLoggerServer(InetAddress.getLoopbackAddress());
        var loggerOne = setup.createLogger();
        var loggerTwo = setup.createLogger();
        Assert.assertNotEquals(loggerOne, loggerTwo);
    }

    @Test
    void testCreateLoggerNoServerName() {
        var setup = new VskLoggerSetup();
        setup.setLoggerServer(InetAddress.getLoopbackAddress());
        assertThrows(IllegalStateException.class, setup::createLogger);
    }

    @Test
    void testCreateLoggerNoInetAddress() {
        var setup = new VskLoggerSetup();
        setup.setLoggerName("Test");
        assertThrows(IllegalStateException.class, setup::createLogger);
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
    void testSetLoggerServer() {
        LoggerSetup setup = new VskLoggerSetup();
        final InetAddress inet = InetAddress.getLoopbackAddress();
        setup.setLoggerServer(inet);
    }
    
}
