/*
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.common;

import ch.hslu.vsk.logger.api.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import nl.jqno.equalsverifier.EqualsVerifier;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Tobias Heller
 */
public class LogMessageTest {

    public LogMessageTest() {
    }

    @Test
    public void testGetUuid() {
        LogMessage m = new LogMessage(LogLevel.INFO, "This is a message");
        assertThat(m.getUuid()).isNotNull();
    }

    @Test
    public void testEquals() {
        LogMessage m = new LogMessage(LogLevel.INFO, "This is a message");
        LogMessage m2 = new LogMessage(LogLevel.DEBUG, "This is a message");

        assertThat(m.equals(m2)).isFalse();
    }

    @Test
    public void testGetContent() {
        String content = "This is a message";
        LogMessage m = new LogMessage(LogLevel.INFO, content);
        assertThat(m.getMessage()).isEqualTo("This is a message");
    }

    @Test
    public void testGetLogLevel() {
        String content = "This is a message";
        LogMessage m = new LogMessage(LogLevel.INFO, content);
        assertThat(m.getLogLevel()).isEqualTo(LogLevel.INFO);
    }

    @Test
    public void testGetCreatedAt() {
        String content = "This is a message";
        Instant now = Instant.now();
        LogMessage m = new LogMessage(LogLevel.INFO, content);
        assertThat(m.getCreatedAt()).isCloseTo(now, within(1, ChronoUnit.SECONDS));
    }

    @Test
    public void testGetReceivedAt() {
        String content = "This is a message";
        LogMessage m = new LogMessage(LogLevel.INFO, content);
        assertThat(m.getReceivedAt()).isNull();

        Instant now = Instant.now();
        m.setReceived();
        assertThat(m.getReceivedAt()).isCloseTo(now, within(1, ChronoUnit.SECONDS));
    }

    @Test
    public void testEqualsContract() {
        EqualsVerifier.forClass(LogMessage.class).withIgnoredFields("receivedAt").verify();
    }
}
