/*
 * LogPersistor.java
 * Created on 22.10.2020
 *
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.server.persistency;

import ch.hslu.vsk.logger.common.LogMessage;

/**
 * Persistable Interface, which specifies how to save a LogMessage Object.
 *
 * @author Silvan Wenk
 */
public interface Persistable {

    /**
     * Serializes the message.
     *
     * @param message The message to save.
     * @return LogMessage as String
     */
    String build(LogMessage message);
}
