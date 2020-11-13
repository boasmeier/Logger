package ch.hslu.vsk.logger.server.persistency;

import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.common.LogMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnhancedPersistTest {
    @Test
    void testBuild() {
        final EnhancedPersist persistable = new EnhancedPersist();
        final LogMessage message = new LogMessage("Test name", LogLevel.ERROR, "Test message");
        message.setReceived();
        final String expected = String.format(
                "ERROR | Message: Test message, Created: %s, Received: %s",
                message.getCreatedAt(),
                message.getReceivedAt());
        final String actual = persistable.build(message);
        assertEquals(expected, actual);
    }

    @Test
    void testBuildNoReceived() {
        final EnhancedPersist persistable = new EnhancedPersist();
        final LogMessage message = new LogMessage("Test name", LogLevel.ERROR, "Test message");
        final String expected = String.format(
                "ERROR | Message: Test message, Created: %s, Received: null",
                message.getCreatedAt());
        final String actual = persistable.build(message);
        assertEquals(expected, actual);
    }
}
