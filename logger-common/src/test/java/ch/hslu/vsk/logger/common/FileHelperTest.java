package ch.hslu.vsk.logger.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FileHelperTest {
    private static String filePath = "." + File.separator + "testConfig";

    @AfterEach
    void tearDown() {
        deleteConfigFile();
    }

    @Test
    void testReadAny() throws IOException {
        var arguments = new HashMap<String, String>();
        arguments.put("key1", "value1");
        arguments.put("key2", "value2");
        arguments.put("key3", "value3");
        createConfigFile(arguments);
        List<String> actual = FileHelper.read(filePath, Arrays.asList("key1", "key3"));
        assertEquals(actual.size(), 2);
        assertEquals(actual.get(0), "value1");
        assertEquals(actual.get(1), "value3");
    }

    @Test
    void testReadEmpty() throws IOException {
        var arguments = new HashMap<String, String>();
        createConfigFile(arguments);
        List<String> actual = FileHelper.read(filePath, Arrays.asList("key1", "key3"));
        assertEquals(actual.size(), 2);
        assertNull(actual.get(0));
        assertNull(actual.get(1));
    }

    @Test
    void testReadGameConfiguration() throws IOException {
        var arguments = new HashMap<String, String>();
        arguments.put("className", "ch.hslu.vsk.logger.component.VskLoggerSetup");
        arguments.put("logLevel", "debug");
        arguments.put("serverIp", "127.0.0.1");
        arguments.put("port", "5050");
        arguments.put("name", "gameInstance1");
        createConfigFile(arguments);
        List<String> actual = FileHelper.read(filePath, Arrays.asList("logLevel", "className", "serverIp", "port", "name"));
        assertEquals(actual.size(), 5);
        assertEquals(actual.get(0), "debug");
        assertEquals(actual.get(1), "ch.hslu.vsk.logger.component.VskLoggerSetup");
        assertEquals(actual.get(2), "127.0.0.1");
        assertEquals(actual.get(3), "5050");
        assertEquals(actual.get(4), "gameInstance1");
    }

    @Test
    void testReadServerConfiguration() throws IOException {
        var arguments = new HashMap<String, String>();
        arguments.put("port", "5050");
        arguments.put("className", "ch.hslu.vsk.logger.server.EnhancedPersistor");
        arguments.put("path", "path/to/file.txt");
        createConfigFile(arguments);
        List<String> actual = FileHelper.read(filePath, Arrays.asList("className", "port", "path"));
        assertEquals(actual.size(), 3);
        assertEquals(actual.get(0), "ch.hslu.vsk.logger.server.EnhancedPersistor");
        assertEquals(actual.get(1), "5050");
        assertEquals(actual.get(2), "path/to/file.txt");
    }

    @Test
    void testReadFileNotFound() {
        final Exception ex = assertThrows(FileNotFoundException.class, () -> {
            List<String> actual = FileHelper.read(filePath, Arrays.asList("className", "port", "path"));
        });
        String expected = filePath + " (The system cannot find the file specified)";
        assertEquals(expected, ex.getMessage());
    }

    private void createConfigFile(Map<String, String> arguments) {
        File file = new File(filePath);
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(file, true))) {
            boolean createdNew = file.createNewFile();
            arguments.forEach((key, value) -> {
                try {
                    buffer.write(String.format("%s=%s", key, value));
                    buffer.newLine();
                }
                catch (IOException ex) {
                    System.out.println("An error occurred while writing: " + ex.getMessage());
                    ex.printStackTrace();
                }
            });
        } catch (IOException ex) {
            System.out.println("An error occurred while writing: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void deleteConfigFile() {
        File file = new File("." + File.separator + "testConfig");
        boolean result = file.delete();
    }
}
