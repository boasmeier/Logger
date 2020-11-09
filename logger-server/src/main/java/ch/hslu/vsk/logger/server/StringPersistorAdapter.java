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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Code of Class StringPersistorAdapter.
 * @author Tobias Heller, Silvan Wenk
 */
public final class StringPersistorAdapter implements LogPersistor {
    private static final Logger LOGGER = Logger.getLogger(LoggerServer.class.getName());
    private static final String DEFAULT_PATH = System.getProperty("user.home") + "Desktop";
    private static final String DEFAULT_FILE_TYPE = "Simple";

    private StringPersistor persistor;
    private FileSelector selector;

    /**
     * Creates and configures a StringPersistorAdapter.
     */
    StringPersistorAdapter() {
        final List<String> arguments = this.getConfigurationArguments();
        String fileType = arguments.get(0);
        String path = arguments.get(1);
        this.persistor = new StringPersistorFile();
        this.selector = new FileSelector(path);
    }

    /**
     * Store a message to the StringPersistor.
     * @param message LogMessage Object to store.
     */
    @Override
    public final void save(final LogMessage message) {
        File file = selector.select(message);
        persistor.setFile(file);
        persistor.save(Instant.now(), message.toString());
    }

    /**
     * Reads the file type and log path from the server configuration file.
     * @return If exception or arguments not found the default configuration is returned.
     */
    private List<String> getConfigurationArguments() {
        List<String> arguments = new ArrayList<String>();
        try {
            var args = FileHelper.read("loggerServerConfig", Arrays.asList("file_type", "path"));
            arguments.add(args.get(0) == null ? DEFAULT_FILE_TYPE : args.get(0));
            arguments.add(args.get(1) == null ? DEFAULT_PATH : args.get(1));
            return arguments;
        } catch (IOException ex) {
            LOGGER.warning("Could not open server configuration file: " + ex.getMessage());
            ex.printStackTrace();
            arguments.add(DEFAULT_FILE_TYPE);
            arguments.add(DEFAULT_PATH);
            return arguments;
        }
    }
}
