package ch.hslu.vsk.logger.server.persistency;

import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.stringpersistor.api.StringPersistor;

import java.time.Instant;

public class EnhancedPersist implements Persistable {
    @Override
    public void save(StringPersistor persistor, LogMessage message) {
        persistor.save(Instant.now(), this.build(message));
    }

    String build(LogMessage message) {
        return String.format(
                "%s | Message: %s, Created: %s, Received: %s",
                message.getLogLevel(),
                message.getMessage(),
                message.getCreatedAt(),
                message.getReceivedAt());
    }
}
