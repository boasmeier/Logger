/*
 * ClientLogPersistor.java
 * Created on 22.10.2020
 *
 * Copyright(c) 2020 Boas Meier.
 * This software is the proprietary information of Boas Meier.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.common.LogMessage;
import org.junit.jupiter.api.Test;

/**
 *
 * @author boasm
 */
public class ConnectionIT {

    //private static final Logger LOG = Logger.getLogger(ConnectionIT.class.getName());
    /**
     * Test to simulate game instance and test disconnection scenarios with server. TODO: Full automation
     */
    @Test
    public void ITSend() {
        Connection serverConnection = new Connection("127.0.0.1", 5050);
        LogMessage msg;
        var counter = 0;
        while (counter < 20) {
            try {
                msg = new LogMessage("TestName", LogLevel.INFO, "TestPayload " + counter);
                Thread.sleep(1000);
                serverConnection.send(msg);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            counter++;
        }
    }
}
