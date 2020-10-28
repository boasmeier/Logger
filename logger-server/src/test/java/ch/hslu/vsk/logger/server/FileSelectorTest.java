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

    @Test
    public void testSelect1() {
        LogMessage message = new LogMessage("Test", LogLevel.INFO, "testmessage");
        FileSelector selector = new FileSelector(message);
        File file = selector.select();
        boolean b = file.equals(new File(""));
        assertEquals(false, b);
    }

    @Test
    public void testSelect2() {
        LogMessage message1 = new LogMessage("Test", LogLevel.INFO, "testmessage");
        LogMessage message2 = new LogMessage("Logger", LogLevel.INFO, "Log.info");
        FileSelector selector1 = new FileSelector(message1);
        FileSelector selector2 = new FileSelector(message2);
        File file1 = selector1.select();
        File file2 = selector2.select();
        boolean b = file1.equals(file2);
        assertEquals(false, b);
    }

    @Test
    public void testSelect3() {
        LogMessage message1 = new LogMessage("Test", LogLevel.INFO, "testmessage");
        LogMessage message2 = new LogMessage("Test", LogLevel.INFO, "testmessage");
        FileSelector selector1 = new FileSelector(message1);
        FileSelector selector2 = new FileSelector(message2);
        File file1 = selector1.select();
        File file2 = selector2.select();
        assertEquals(file1, file2);
    }

    @Test
    public void testSelect4() {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String home = System.getProperty("user.home");
        LogMessage message = new LogMessage("Test", LogLevel.INFO, "testmessage");
        FileSelector selector = new FileSelector(message);
        File file = selector.select();
        String path = home + File.separator + "Desktop" + File.separator + message.getLoggerName() + "_" + formatter.format(new Date()) + "_persistor.log";
        assertEquals(file.getAbsolutePath(), path);
    }
}
