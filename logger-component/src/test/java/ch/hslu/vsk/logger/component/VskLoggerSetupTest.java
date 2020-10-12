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
    @Test
    void testCreateLogger() {
        var setup = new VskLoggerSetup();
        setup.setLoggerName("Test");
        setup.setLoggerServer(InetAddress.getLoopbackAddress());
        var loggerOne = setup.createLogger();
        var loggerTwo = setup.createLogger();
        assertNotEquals(loggerOne, loggerTwo);
    }

    @Test
    void testCreateLoggerNoServerName() {
        var setup = new VskLoggerSetup();
        setup.setLoggerServer(InetAddress.getLoopbackAddress());
        var message = assertThrows(IllegalStateException.class, setup::createLogger)
                .getMessage();
        assertEquals("Cannot create a logger without a name.", message);

    }

    @Test
    void testCreateLoggerNoInetAddress() {
        var setup = new VskLoggerSetup();
        setup.setLoggerName("Test");
        var message = assertThrows(IllegalStateException.class, setup::createLogger)
                .getMessage();
        assertEquals("Cannot create a logger without a server IP Address.", message);
    }

    @Test
    void testCreateLoggerInetAddressNotReachable() throws UnknownHostException {
        var fakeAddress = "127.0.0.0";
        var setup = new VskLoggerSetup();
        setup.setLoggerName("Test");
        setup.setLoggerServer(InetAddress.getByName(fakeAddress));
        var message = assertThrows(IllegalStateException.class, setup::createLogger)
                .getMessage();
        assertEquals(String.format("IP address (%s) unreachable.", fakeAddress), message);
    }

    @Test
    void testIpAddressIsReachable() {
        var loopbackAddress = InetAddress.getLoopbackAddress();
        var setup = new VskLoggerSetup();
        assertTrue(setup.ipAddressIsReachable(loopbackAddress));
    }

    @Test
    void testIpAddressIsReachableFalse() throws UnknownHostException {
        var fakeAddress = InetAddress.getByName("127.0.0.0");
        var setup = new VskLoggerSetup();
        assertFalse(setup.ipAddressIsReachable(fakeAddress));
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
