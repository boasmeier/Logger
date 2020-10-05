/*
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.api.Logger;
import ch.hslu.vsk.logger.api.LoggerSetup;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Tobias Heller
 */

//TODO (hellerto): Verify Results
public class VskLoggerTest {

    public VskLoggerTest() {
    }

    @Test
    public void testSetMinimumLevel() {
        LoggerSetup setup = new VskLoggerSetup();
        setup.setMinimumLevel(LogLevel.DEBUG);
        Logger logger = setup.createLogger();
        logger.setMinimumLevel(LogLevel.WARN);
    }

    @Test
    public void testTrace() {
        LoggerSetup setup = new VskLoggerSetup();
        setup.setMinimumLevel(LogLevel.TRACE);
        Logger logger = setup.createLogger();
        logger.trace("a Message");
    }

    @Test
    public void testDebug() {
        LoggerSetup setup = new VskLoggerSetup();
        setup.setMinimumLevel(LogLevel.DEBUG);
        Logger logger = setup.createLogger();
        logger.debug("a Message");
    }

    @Test
    public void testInfo() {
        LoggerSetup setup = new VskLoggerSetup();
        setup.setMinimumLevel(LogLevel.DEBUG);
        Logger logger = setup.createLogger();
        logger.info("a Message");
    }

    @Test
    public void testWarn() {
        LoggerSetup setup = new VskLoggerSetup();
        setup.setMinimumLevel(LogLevel.DEBUG);
        Logger logger = setup.createLogger();
        logger.warn("a Message");
    }

    @Test
    public void testFatal() {
        LoggerSetup setup = new VskLoggerSetup();
        setup.setMinimumLevel(LogLevel.DEBUG);
        Logger logger = setup.createLogger();
        logger.fatal("A Fatal Message", new Exception("Oh no an exception"));
    }

    @Test
    public void testError() {
        LoggerSetup setup = new VskLoggerSetup();
        setup.setMinimumLevel(LogLevel.DEBUG);
        Logger logger = setup.createLogger();
        logger.error("A Error Message", new Exception("Oh no an exception"));
    }

    @Test
    public void testLog() {
        LoggerSetup setup = new VskLoggerSetup();
        setup.setMinimumLevel(LogLevel.DEBUG);
        Logger logger = setup.createLogger();
        logger.log(LogLevel.DEBUG, "A Debug Message");
    }
}
