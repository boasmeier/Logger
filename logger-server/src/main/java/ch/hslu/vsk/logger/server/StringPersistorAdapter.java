package ch.hslu.vsk.logger.server;

import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.stringpersistor.impl.StringPersistorFile;

import java.time.Instant;

public class StringPersistorAdapter implements LogPersistor {
    @Override
    public void save(LogMessage message) {
        final StringPersistorFile stringPersistorFile = new StringPersistorFile();
        //TODO: Instead of message.toString(), do more sophisticated adaptation.
        stringPersistorFile.save(Instant.now(), message.toString());
    }
}
