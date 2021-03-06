/*
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.common;

import ch.hslu.vsk.logger.api.LogLevel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
class LogMessageTest {

    LogMessageTest() {
    }

    @Test
    void testGetUuid() {
        LogMessage m = new LogMessage("Test", LogLevel.INFO, "This is a message");
        assertThat(m.getUuid()).isNotNull();
    }

    @Test
    void testEquals() {
        LogMessage m = new LogMessage("Test", LogLevel.INFO, "This is a message");
        LogMessage m2 = new LogMessage("Test", LogLevel.DEBUG, "This is a message");

        assertThat(m.equals(m2)).isFalse();
    }

    @Test
    void testGetContent() {
        String content = "This is a message";
        LogMessage m = new LogMessage("Test", LogLevel.INFO, content);
        assertThat(m.getMessage()).isEqualTo("This is a message");
    }

    @Test
    void testGetLogLevel() {
        String content = "This is a message";
        LogMessage m = new LogMessage("Test", LogLevel.INFO, content);
        assertThat(m.getLogLevel()).isEqualTo(LogLevel.INFO);
    }

    @Test
    void testGetCreatedAt() {
        String content = "This is a message";
        Instant now = Instant.now();
        LogMessage m = new LogMessage("Test", LogLevel.INFO, content);
        assertThat(m.getCreatedAt()).isCloseTo(now, within(1, ChronoUnit.SECONDS));
    }

    @Test
    void testGetReceivedAt() {
        String content = "This is a message";
        LogMessage m = new LogMessage("Test", LogLevel.INFO, content);
        assertThat(m.getReceivedAt()).isNull();

        Instant now = Instant.now();
        m.setReceived();
        assertThat(m.getReceivedAt()).isCloseTo(now, within(1, ChronoUnit.SECONDS));
    }

    @Test
    void testEqualsContract() {
        EqualsVerifier.forClass(LogMessage.class).withIgnoredFields("receivedAt").verify();
    }

    @Test
    void testSerializable() throws IOException, ClassNotFoundException {
        LogMessage m1 = new LogMessage("Test", LogLevel.DEBUG, "This is a message");
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(m1);
        }

        LogMessage m2;
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            m2 = (LogMessage) ois.readObject();
        }
        assertThat(m1.equals(m2)).isTrue();
    }

    @Test
    void testGetLoggerName() {
        String content = "This is a message";
        LogMessage m = new LogMessage("Logger01", LogLevel.INFO, content);
        assertThat(m.getLoggerName()).isEqualTo("Logger01");
    }
}
