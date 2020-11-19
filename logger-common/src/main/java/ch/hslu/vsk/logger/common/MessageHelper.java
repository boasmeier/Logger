/**
 * MessageHelper.java
 * Created on 05.10.2020
 *
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.common;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Code of Class MessageHelper.
 *
 * @author Tobias Heller
 */
public class MessageHelper {
    private MessageHelper() {}

    /**
     * Returns a String for an exception that should be logged.
     *
     * @param message LogMessage to Log.
     * @param exception Exception to log.
     * @return Exception as a String.
     */
    public static String ExceptionMessageToString(final String message, final Throwable exception) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintStream ps = new PrintStream(baos)) {
            exception.printStackTrace(ps);
        }
        return message +  "- Exception:[" + baos.toString() + "]";
    }
}
