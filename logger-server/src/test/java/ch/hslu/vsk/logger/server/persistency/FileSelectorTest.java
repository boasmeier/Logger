/*
 * Copyright(c) 2020 Thomas Goldenberger.
 * This software is the proprietary information of Thomas Goldenberger.
 */
package ch.hslu.vsk.logger.server.persistency;

import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.common.LogMessage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Thomas Goldenberger
 */
class FileSelectorTest {

    private static final String PATH = System.getProperty("user.home") + File.separator + "Desktop";

    @Test
    void testSelect1() {
        LogMessage message = new LogMessage("Test", LogLevel.INFO, "testmessage");
        FileSelector selector = new FileSelector(PATH);
        File file = selector.select(message);
        boolean b = file.equals(new File(""));
        assertFalse(b);
    }

    @Test
    void testSelect2() {
        LogMessage message1 = new LogMessage("Test", LogLevel.INFO, "testmessage");
        LogMessage message2 = new LogMessage("Logger", LogLevel.INFO, "Log.info");
        FileSelector selector = new FileSelector(PATH);
        File file1 = selector.select(message1);
        File file2 = selector.select(message2);
        boolean b = file1.equals(file2);
        assertFalse(b);
    }

    @Test
    void testSelect3() {
        LogMessage message1 = new LogMessage("Test", LogLevel.INFO, "testmessage");
        LogMessage message2 = new LogMessage("Test", LogLevel.INFO, "testmessage");
        FileSelector selector = new FileSelector(PATH);
        File file1 = selector.select(message1);
        File file2 = selector.select(message2);
        assertEquals(file1, file2);
    }

    @Test
    void testSelect4() {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String home = System.getProperty("user.home");
        LogMessage message = new LogMessage("Test", LogLevel.INFO, "testmessage");
        FileSelector selector = new FileSelector(PATH);
        File file = selector.select(message);
        String path = home + File.separator + "Desktop" + File.separator + message.getLoggerName() + "_" + formatter.format(new Date()) + "_persistor.log";
        assertEquals(file.getAbsolutePath(), path);
    }
}
