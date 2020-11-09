/**
 * FileSelector.java
 * Created on 28.10.2020
 *
 * Copyright(c) 2020 Thomas Goldenberger.
 * This software is the proprietary information of Thomas Goldenberger.
 */
package ch.hslu.vsk.logger.server.persistency;

import ch.hslu.vsk.logger.common.LogMessage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Code of Class FileSelector.
 * @author Thomas Goldenberger, Silvan Wenk
 */
class FileSelector {
    private String logFilePath;

    /**
     * Creates a selector which configures where the LogMessage should be saved.
     * @param logFilePath Path to store the log files.
     */
    FileSelector(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    /**
     * Creates a file to hstore te logs.
     * @param message LogMessage Object to store.
     * @return Returns a new file to store the LogMessage.
     */
    File select(LogMessage message) {
        String source = message.getLoggerName();
        String filePath = this.logFilePath + File.separator + source + "_" + getDate() + "_persistor.log";
        return new File(filePath);
    }

    private String getDate() {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(new Date());
    }
}
