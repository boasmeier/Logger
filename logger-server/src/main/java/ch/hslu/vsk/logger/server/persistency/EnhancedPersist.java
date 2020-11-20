package ch.hslu.vsk.logger.server.persistency;

import ch.hslu.vsk.logger.common.LogMessage;

public final class EnhancedPersist implements Persistable {

    /**
     * Uses toString to serialize the message.
     *
     * @param message The message to save.
     * @return LogMessage as formatted string: [LogLevel] | Message: [Message], Created: [CreatedAt], Received:
     * [RecievedAt]
     */
    @Override
    public final String build(final LogMessage message) {
        return String.format(
                "%s | Message: %s, Created: %s, Received: %s",
                message.getLogLevel(),
                message.getMessage(),
                message.getCreatedAt(),
                message.getReceivedAt());
    }
}
