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
public class VskLoggerTest {
    
    public VskLoggerTest() {
    }

    @Test
    public void testSetMinimumLevel() {
        LoggerSetup setup = new VskLoggerSetup();
        setup.setMinimumLevel(LogLevel.DEBUG);
        Logger logger = setup.createLogger();
        logger.info("Info MSG");
    }

    @Test
    public void testTrace() {
    }

    @Test
    public void testDebug() {
    }

    @Test
    public void testInfo() {
    }

    @Test
    public void testWarn() {
    }

    @Test
    public void testFatal() {
    }

    @Test
    public void testError() {
    }

    @Test
    public void testLog() {
    }
    
}
