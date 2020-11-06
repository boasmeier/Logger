/*
 * StringPersistorAdapter.java
 * Created on 22.10.2020
 * 
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.server;

import ch.hslu.vsk.logger.common.FileHelper;
import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.stringpersistor.api.StringPersistor;
import ch.hslu.vsk.stringpersistor.impl.StringPersistorFile;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * Code of Class StringPersistorAdapter.
 *
 * @author Tobias Heller
 */
public final class StringPersistorAdapter implements LogPersistor {

    private StringPersistor persistor;
    private FileSelector selector;

    StringPersistorAdapter() {
        String fileType = "Simple"; //TODO: Use Strategy to instantiate correct fileType
        String path = ""; //TODO: Default log path
        try {
            List<String> arguments = FileHelper.read("loggerServerConfig", Arrays.asList("file_type", "path"));
            fileType = arguments.get(0);
            path = arguments.get(1);
        } catch (IOException ex) {
            //TODO: handle
        }

        this.persistor = new StringPersistorFile();
        this.selector = new FileSelector(path);
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
