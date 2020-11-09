package ch.hslu.vsk.logger.server.persistency;

import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.stringpersistor.api.StringPersistor;

import java.time.Instant;

public class BasicPersist implements Persistable {
    /**
     * Uses toString to serialize the message.
     * @param persistor The StringPersistor implementation which saves the message to the file.
     * @param message The message to save.
     */
    @Override
    public void save(StringPersistor persistor, LogMessage message) {
        persistor.save(Instant.now(), message.toString());
    }
}
