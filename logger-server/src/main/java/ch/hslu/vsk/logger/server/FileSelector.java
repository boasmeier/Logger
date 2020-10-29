/**
 * FileSelector.java
 * Created on 28.10.2020
 *
 * Copyright(c) 2020 Thomas Goldenberger.
 * This software is the proprietary information of Thomas Goldenberger.
 */
package ch.hslu.vsk.logger.server;

import ch.hslu.vsk.logger.common.LogMessage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Code of Class FileSelector.
 *
 * @author Thomas Goldenberger
 */
public class FileSelector {


    /**
     * Constructor of class FileSelector. Creates a selector which decides where the logMessage should be saved.
     */
    public FileSelector() {
    }

    /**
     * Creates a file to store the logs.
     *
     * @return Returns a new file to store the LogMessage.
     */
    public File select(LogMessage message) {
        String source = message.getLoggerName();
        String home = System.getProperty("user.home");
        String filePath = home + File.separator + "Desktop" + File.separator + source + "_" + getDate() + "_persistor.log";
        File file = new File(filePath);
        return file;
    }

    private String getDate() {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(new Date());
    }
}
