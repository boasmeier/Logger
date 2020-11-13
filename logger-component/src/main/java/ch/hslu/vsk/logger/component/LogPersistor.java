/*
 * LogPersistor.java
 * Created on 22.10.2020
 *
 * Copyright(c) 2020 Boas Meier.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.common.LogMessage;
import java.util.Queue;

/**
 * Code of Interface LogPersistor.
 *
 * @author Boas Meier
 */
public interface LogPersistor {

    /**
     * Saves a LogMessage in a persistent way.
     *
     * @param message The message to save.
     */
    public void save(final LogMessage message);

    /**
     * Returns all missed messages.
     *
     * @return A list of the PersistedStrings
     */
    public Queue<LogMessage> get();
}
