package ch.hslu.vsk.logger.server.persistency;

import ch.hslu.vsk.logger.common.LogMessage;

public class BasicPersist implements Persistable {
    /**
     * Uses toString to serialize the message.
     * @param message The message to save.
     */
    @Override
    public String build(LogMessage message) {
        return message.toString();
    }
}
