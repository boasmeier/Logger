package ch.hslu.vsk.logger.server.persistency;

import ch.hslu.vsk.logger.common.LogMessage;

public final class BasicPersist implements Persistable {

    /**
     * Uses toString to serialize the message.
     *
     * @param message The message to save.
     * @return toString() of LogMessage
     */
    @Override
    public String build(final LogMessage message) {
        return message.toString();
    }
}
