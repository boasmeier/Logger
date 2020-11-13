/**
 * FileSelector.java
 * Created on 09.11.2020
 *
 * Copyright(c) 2020 Boas Meier.
 * This software is the proprietary information of Boas Meier.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.common.LogMessage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Code of Class FileSelector.
 *
 * @author Boas Meier
 */
public final class FileSelector {

    /**
     * Constructor of class FileSelector. Creates a selector which decides where the logMessage should be saved.
     */
    public FileSelector() {
    }

    /**
     * Creates a file to store the logs.
     *
     * @param message LogMessage Object to store.
     * @return Returns a new file to store the LogMessage.
     */
    public final File select(final LogMessage message) {
        String source = message.getLoggerName();
        String filePath = "." + File.separator + source + "_" + getDate() + "_persistor.log";
        File file = new File(filePath);
        return file;
    }

    private String getDate() {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(new Date());
    }
}
