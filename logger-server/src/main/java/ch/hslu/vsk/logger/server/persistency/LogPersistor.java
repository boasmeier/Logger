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
 * @author Tobias Heller
 */
public interface LogPersistor {

    /**
     * Serializes the message and forward it to the StringPersitor.
     *
     * @param message The message to save.
     */
    void save(final LogMessage message);
}
