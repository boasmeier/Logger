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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author boasm
 */
public class StringPersistorAdapterTest {

    public StringPersistorAdapterTest() {
    }

    @BeforeAll
    public static void setUpClass() {
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
     * Test of save method, of class StringPersistorAdapter.
     */
    @Test
    public void testSaveAndGet() {
        LogMessage msg = new LogMessage("TestName", LogLevel.INFO, "TestPayload");
        LogPersistor persistor = new StringPersistorAdapter();
        persistor.save(msg);
        Queue<LogMessage> messages = persistor.get();
        assertThat(messages.contains(msg)).isTrue();
    }
}
