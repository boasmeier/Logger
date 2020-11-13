/*
 * LogPersistor.java
 * Created on 22.10.2020
 * 
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
*/
package ch.hslu.vsk.logger.server.persistency;

import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.stringpersistor.api.StringPersistor;

/**
 * Persistable Interface, which specifies how to save a LogMessage Object.
 * @author Silvan Wenk
 */
public interface Persistable {
    /**
     * Serializes the message and forward it to the StringPersitor.
     * @param persistor The StringPersistor implementation which saves the message to the file.
     * @param message The message to save.
     */
    void save(final StringPersistor persistor, final LogMessage message);
}
