package ch.hslu.vsk.logger.server;

import ch.hslu.vsk.logger.common.LogMessage;

public interface LogPersistor {
    void save(LogMessage message);
}
