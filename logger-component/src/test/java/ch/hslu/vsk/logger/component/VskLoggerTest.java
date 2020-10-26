/*
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.api.Logger;
import ch.hslu.vsk.logger.api.LoggerSetup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class VskLoggerTest {
    private static LoggerSetup setup;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 5050;

    @BeforeAll
    static void setupLogger() {
        setup = new VskLoggerSetup();
        setup.setLoggerName("Test");
        setup.setMinimumLevel(LogLevel.DEBUG);
        setup.setServerIP(HOST);
        setup.setServerPort(PORT);
        assertThat(setup).isNotNull();
    }

    @Test
    void testSetMinimumLevel() {
        Logger logger = setup.createLogger();
        var expected = LogLevel.WARN;
        logger.setMinimumLevel(expected);
        var actual = logger.getMinimumLevel();
        assertEquals(expected, actual);
    }

    @Test
    void testTrace() {
        Logger logger = setup.createLogger();
        logger.trace("a Message");
    }

    @Test
    void testDebug() {
        Logger logger = setup.createLogger();
        logger.debug("a Message");
    }

    @Test
    void testInfo() {
        Logger logger = setup.createLogger();
        logger.info("a Message");
    }

    @Test
    void testWarn() {
        Logger logger = setup.createLogger();
        logger.warn("a Message");
    }

    @Test
    void testFatal() {
        Logger logger = setup.createLogger();
        logger.fatal("A Fatal Message", new Exception("Oh no an exception"));
    }

    @Test
    void testError() {
        Logger logger = setup.createLogger();
        logger.error("A Error Message", new Exception("Oh no an exception"));
    }
}
