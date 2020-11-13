package ch.hslu.vsk.logger.server.persistency;

import ch.hslu.vsk.logger.common.LogMessage;

public class EnhancedPersist implements Persistable {
    /**
     * Uses toString to serialize the message.
     * @param message The message to save.
     */
    @Override
    public String build(LogMessage message) {
        return String.format(
                "%s | Message: %s, Created: %s, Received: %s",
                message.getLogLevel(),
                message.getMessage(),
                message.getCreatedAt(),
                message.getReceivedAt());
    }
}
