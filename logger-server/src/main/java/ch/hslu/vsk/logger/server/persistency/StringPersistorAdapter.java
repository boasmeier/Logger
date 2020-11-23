/*
 * StringPersistorAdapter.java
 * Created on 22.10.2020
 *
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.server.persistency;

import ch.hslu.vsk.logger.common.FileHelper;
import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.logger.server.LoggerServer;
import ch.hslu.vsk.stringpersistor.api.StringPersistor;
import ch.hslu.vsk.stringpersistor.impl.StringPersistorFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Code of Class StringPersistorAdapter.
 *
 * @author Tobias Heller, Silvan Wenk
 */
public final class StringPersistorAdapter implements LogPersistor {

    private static final Logger LOGGER = Logger.getLogger(LoggerServer.class.getName());
    private static final String DEFAULT_PATH = System.getProperty("user.home") + File.separator + "Desktop";
    private static final String DEFAULT_FILE_TYPE = "basic";

    private Persistable persistable;
    private final StringPersistor persistor;
    private final FileSelector selector;

    /**
     * Creates and configures a StringPersistorAdapter.
     */
    public StringPersistorAdapter() {
        final List<String> arguments = this.getConfigurationArguments();
        this.setPersistableStrategy(arguments.get(0));
        this.persistor = new StringPersistorFile();
        this.selector = new FileSelector(arguments.get(1));
    }

    /**
     * Store a message to the StringPersistor.
     *
     * @param message LogMessage Object to store.
     */
    @Override
    public void save(final LogMessage message) {
        File file = selector.select(message);
        persistor.setFile(file);
        persistor.save(Instant.now(), persistable.build(message));
    }

    /**
     * Reads the file type and log path from the server configuration file.
     *
     * @return If exception or arguments not found the default configuration is returned.
     */
    private List<String> getConfigurationArguments() {
        List<String> arguments = new ArrayList<>();
        try {
            var args = FileHelper.read("loggerServerConfig", Arrays.asList("file_type", "path"));
            arguments.add(args.get(0) == null ? DEFAULT_FILE_TYPE : args.get(0));
            String pathArgument = args.get(1);
            try {
                if (pathArgument == null || pathArgument.equals("") || Files.notExists(Paths.get(pathArgument))) {
                    LOGGER.warning("The specified path for the log files does not exist, " +
                            "switching to user's desktop.");
                    throw new InvalidPathException("", "");
                }
                arguments.add(pathArgument);
            } catch (InvalidPathException ex) {
                arguments.add(DEFAULT_PATH);
            }
            return arguments;
        } catch (IndexOutOfBoundsException | IOException ex) {
            LOGGER.warning("Could not open server configuration file: " + ex.getMessage());
            arguments = new ArrayList<>();
            arguments.add(DEFAULT_FILE_TYPE);
            arguments.add(DEFAULT_PATH);
            return arguments;
        }
    }

    /**
     * Sets the correct implementation strategy.
     *
     * @param type Argument of the server configuration file.
     */
    private void setPersistableStrategy(final String type) {
        switch (FileTypeExtension.getEnum(type)) {
            case Enhanced:
                this.persistable = new EnhancedPersist();
                break;
            case Xml:
                this.persistable = new XmlPersist();
                break;
            case Json:
                this.persistable = new JsonPersist();
                break;
            default:
                this.persistable = new BasicPersist();
                break;
        }
    }
}
