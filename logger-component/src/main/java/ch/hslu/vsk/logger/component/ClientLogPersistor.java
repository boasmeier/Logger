/*
 * ClientLogPersistor.java
 * Created on 22.10.2020
 *
 * Copyright(c) 2020 Boas Meier.
 * This software is the proprietary information of Boas Meier.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.common.LogMessage;
import java.util.Queue;

/**
 * Code of Interface ClientLogPersistor.
 *
 * @author Boas Meier
 */
public interface ClientLogPersistor {

    /**
     * Saves a LogMessage in a persistent way.
     *
     * @param message The message to save.
     */
    void save(LogMessage message);

    /**
     * Returns all missed messages.
     *
     * @return A list of the PersistedStrings
     */
    Queue<LogMessage> get();
}
