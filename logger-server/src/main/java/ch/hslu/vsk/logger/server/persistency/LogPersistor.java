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
 * LogPersistor Interface, which specifies how to save a LogMessage Object.
 * @author Tobias Heller
 */
public interface LogPersistor {
    /**
     * Saves a LogMessage in a persistent way.
     * @param message The message to save.
     */
    public void save(final LogMessage message);
}
