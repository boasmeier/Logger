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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.InetAddress;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 *
 * @author Silvan Wenk
 */
class VskLoggerSetupTest {
    private static LoggerSetup setup;
    private static final NetworkService mockNetworkService = Mockito.mock(NetworkService.class);
    private static final InetAddress inetAddress = InetAddress.getLoopbackAddress();

    @BeforeAll
    static void setup() {
        when(mockNetworkService.ipAddressIsReachable(inetAddress)).thenReturn(true);
    }

    @Test
    void testCreateLogger() {
        setup = new VskLoggerSetup(mockNetworkService);
        setup.setLoggerName("Test");
        setup.setLoggerServer(inetAddress);
        var loggerOne = setup.createLogger();
        var loggerTwo = setup.createLogger();
        assertNotEquals(loggerOne, loggerTwo);
    }

    @Test
    void testCreateLoggerNoServerName() {
        setup = new VskLoggerSetup(mockNetworkService);
        setup.setLoggerServer(InetAddress.getLoopbackAddress());
        var message = assertThrows(IllegalStateException.class, setup::createLogger)
                .getMessage();
        assertEquals("Cannot create a logger without a name.", message);

    }

    @Test
    void testCreateLoggerNoInetAddress() {
        setup = new VskLoggerSetup(mockNetworkService);
        setup.setLoggerName("Test");
        var message = assertThrows(IllegalStateException.class, setup::createLogger)
                .getMessage();
        assertEquals("Cannot create a logger without a server IP Address.", message);
    }

    @Test
    void testSetMinimumLevel() {
        LoggerSetup setup = new VskLoggerSetup(mockNetworkService);
        setup.setMinimumLevel(LogLevel.ERROR);
    }

    @Test
    void testSetLoggerName() {
        LoggerSetup setup = new VskLoggerSetup(mockNetworkService);
        setup.setLoggerName("Test");
    }

    @Test
    void testSetLoggerServer() {
        LoggerSetup setup = new VskLoggerSetup(mockNetworkService);
        final InetAddress inet = InetAddress.getLoopbackAddress();
        setup.setLoggerServer(inet);
    }

}
