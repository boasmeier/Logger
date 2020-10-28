/*
 * StringPersistorAdapter.java
 * Created on 22.10.2020
 * 
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.server;

import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.stringpersistor.api.StringPersistor;
import ch.hslu.vsk.stringpersistor.impl.StringPersistorFile;
import java.io.File;
import java.time.Instant;

/**
 * Code of Class StringPersistorAdapter.
 *
 * @author Tobias Heller
 */
public final class StringPersistorAdapter implements LogPersistor {

    private StringPersistor persistor;

    public StringPersistorAdapter() {
        this.persistor = new StringPersistorFile();
    }

    /**
     * Store a message to the StringPersistor.
     *
     * @param message LogMessage Object to store.
     */
    @Override
    public final void save(final LogMessage message) {
        FileSelector selector = new FileSelector(message);
        File file = selector.select();
        persistor.setFile(file);
        persistor.save(Instant.now(), message.toString());
    }
}
