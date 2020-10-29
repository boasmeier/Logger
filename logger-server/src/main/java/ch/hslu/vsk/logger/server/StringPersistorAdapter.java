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
    private FileSelector selector;

    public StringPersistorAdapter() {
        this.persistor = new StringPersistorFile();
        this.selector = new FileSelector();
    }

    /**
     * Store a message to the StringPersistor.
     *
     * @param message LogMessage Object to store.
     */
    @Override
    public final void save(final LogMessage message) {
        File file = selector.select(message);
        persistor.setFile(file);
        persistor.save(Instant.now(), message.toString());
    }
}
