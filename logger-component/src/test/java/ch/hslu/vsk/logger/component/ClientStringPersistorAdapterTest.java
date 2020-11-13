/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.common.LogMessage;
import java.util.Queue;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author boasm
 */
public class ClientStringPersistorAdapterTest {

    /**
     * Test of save method, of class ClientStringPersistorAdapter.
     */
    @Test
    public void testSaveAndGet() {
        LogMessage msg = new LogMessage("TestName", LogLevel.INFO, "TestPayload");
        ClientLogPersistor persistor = new ClientStringPersistorAdapter();
        persistor.save(msg);
        Queue<LogMessage> messages = persistor.get();
        assertThat(messages.contains(msg)).isTrue();
    }
}
