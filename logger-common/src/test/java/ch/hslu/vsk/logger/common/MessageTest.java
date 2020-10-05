/*
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.common;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;


/**
 *
 * @author Tobias Heller
 */
public class MessageTest {
    
    public MessageTest() {
    }

    @Test
    public void testGetUuid() {
        Message m = new Message("This is a message", "INFO");
        assertThat(m.getUuid()).isNotNull();
    }

    @Test
    public void testGetContent() {
        String content = "This is a message";
        Message m = new Message(content, "INFO");
        assertThat(m.getContent()).equals("This is a message");
    }

    @Test
    public void testGetLogLevel() {
    }

    @Test
    public void testGetCreatedAt() {
    }

    @Test
    public void testGetReceivedAt() {
    }

    @Test
    public void testSetReceived() {
    }
    
}
