/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.common.LogMessage;
import java.io.IOException;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author boasm
 */
public class ConnectionTest {

    private static final Logger LOG = Logger.getLogger(ConnectionTest.class.getName());

    public ConnectionTest() {
    }

    @BeforeAll
    public static void setUpClass() throws IOException {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of send method, of class Connection.
     */
    @Test
    public void ITSend() {
        Connection serverConnection = new Connection("127.0.0.1", 5050);
        LogMessage msg = new LogMessage("TestName", LogLevel.INFO, "TestPayload");
        var counter = 0;
        while (counter < 20) {
            try {
                Thread.sleep(1000);
                serverConnection.send(msg);
                //LOG.info("isClosed: " + serverConnection.isClosed());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            counter++;
        }
    }
}
