package ch.hslu.vsk.logger.server.persistency;

import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.stringpersistor.api.StringPersistor;

public class EnhancedPersist implements Persistable {
    @Override
    public void save(StringPersistor persistor, LogMessage message) {
        throw new UnsupportedOperationException();
    }
}
