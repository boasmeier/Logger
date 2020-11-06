/*
 * Copyright(c) 2020 Thomas Goldenberger.
 * This software is the proprietary information of Thomas Goldenberger.
 */
package ch.hslu.vsk.logger.server;

import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.common.LogMessage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Thomas Goldenberger
 */
public class FileSelectorTest {
    private static String filePath = System.getProperty("user.home") + File.separator + "Desktop";

    @Test
    public void testSelect1() {
        LogMessage message = new LogMessage("Test", LogLevel.INFO, "testmessage");
        FileSelector selector = new FileSelector(filePath);
        File file = selector.select(message);
        boolean b = file.equals(new File(""));
        assertEquals(false, b);
    }

    @Test
    public void testSelect2() {
        LogMessage message1 = new LogMessage("Test", LogLevel.INFO, "testmessage");
        LogMessage message2 = new LogMessage("Logger", LogLevel.INFO, "Log.info");
        FileSelector selector = new FileSelector(filePath);
        File file1 = selector.select(message1);
        File file2 = selector.select(message2);
        boolean b = file1.equals(file2);
        assertEquals(false, b);
    }

    @Test
    public void testSelect3() {
        LogMessage message1 = new LogMessage("Test", LogLevel.INFO, "testmessage");
        LogMessage message2 = new LogMessage("Test", LogLevel.INFO, "testmessage");
        FileSelector selector = new FileSelector(filePath);
        File file1 = selector.select(message1);
        File file2 = selector.select(message2);
        assertEquals(file1, file2);
    }

    @Test
    public void testSelect4() {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String home = System.getProperty("user.home");
        LogMessage message = new LogMessage("Test", LogLevel.INFO, "testmessage");
        FileSelector selector = new FileSelector(filePath);
        File file = selector.select(message);
        String path = home + File.separator + "Desktop" + File.separator + message.getLoggerName() + "_" + formatter.format(new Date()) + "_persistor.log";
        assertEquals(file.getAbsolutePath(), path);
    }
}
