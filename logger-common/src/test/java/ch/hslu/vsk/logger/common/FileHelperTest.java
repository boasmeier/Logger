package ch.hslu.vsk.logger.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class FileHelperTest {

    private static final String PATH = "." + File.separator + "testConfig";

    @AfterEach
    void tearDown() {
        deleteConfigFile();
    }

    /**
     * Tests creating and reading an example configuration file.
     */
    @Test
    void testReadAny() throws IOException {
        var arguments = new HashMap<String, String>();
        arguments.put("key1", "value1");
        arguments.put("key2", "value2");
        arguments.put("key3", "value3");
        createConfigFile(arguments);
        List<String> actual = FileHelper.read(PATH, Arrays.asList("key1", "key3"));
        assertEquals(actual.size(), 2);
        assertEquals(actual.get(0), "value1");
        assertEquals(actual.get(1), "value3");
    }

    /**
     * Verifies that result is null when trying to read non-existing arguments from the file.
     */
    @Test
    void testReadEmpty() throws IOException {
        var arguments = new HashMap<String, String>();
        createConfigFile(arguments);
        List<String> actual = FileHelper.read(PATH, Arrays.asList("key1", "key3"));
        assertEquals(actual.size(), 2);
        assertNull(actual.get(0));
        assertNull(actual.get(1));
    }

    /**
     * Tests creating and reading a sample of the game configuration file.
     */
    @Test
    void testReadGameConfiguration() throws IOException {
        var arguments = new HashMap<String, String>();
        arguments.put("className", "ch.hslu.vsk.logger.component.VskLoggerSetup");
        arguments.put("logLevel", "debug");
        arguments.put("serverIp", "127.0.0.1");
        arguments.put("port", "5050");
        arguments.put("name", "gameInstance1");
        createConfigFile(arguments);
        List<String> actual = FileHelper
                .read(PATH, Arrays.asList("logLevel", "className", "serverIp", "port", "name"));
        assertEquals(actual.size(), 5);
        assertEquals(actual.get(0), "debug");
        assertEquals(actual.get(1), "ch.hslu.vsk.logger.component.VskLoggerSetup");
        assertEquals(actual.get(2), "127.0.0.1");
        assertEquals(actual.get(3), "5050");
        assertEquals(actual.get(4), "gameInstance1");
    }

    /**
     * Tests creating and reading a sample of the server configuration file.
     */
    @Test
    void testReadServerConfiguration() throws IOException {
        var arguments = new HashMap<String, String>();
        arguments.put("port", "5050");
        arguments.put("className", "ch.hslu.vsk.logger.server.EnhancedPersistor");
        arguments.put("path", "path/to/file.txt");
        createConfigFile(arguments);
        List<String> actual = FileHelper.read(PATH, Arrays.asList("className", "port", "path"));
        assertEquals(actual.size(), 3);
        assertEquals(actual.get(0), "ch.hslu.vsk.logger.server.EnhancedPersistor");
        assertEquals(actual.get(1), "5050");
        assertEquals(actual.get(2), "path/to/file.txt");
    }

    /**
     * Tests if the FileNotFoundException is thrown when trying to read the config file without creating it.
     */
    @Test
    void testReadFileNotFound() {
        FileNotFoundException ex = assertThrows(FileNotFoundException.class, () -> FileHelper
                .read(PATH, Arrays.asList("className", "port", "path")));
        assertThat(ex.getMessage()).isNotEqualTo(null);
    }

    private void createConfigFile(final HashMap<String, String> arguments) {
        File file = new File(PATH);
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(file, true))) {
            //boolean createdNew = file.createNewFile();
            arguments.forEach((key, value) -> {
                try {
                    buffer.write(String.format("%s=%s", key, value));
                    buffer.newLine();
                } catch (IOException ex) {
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
        file.delete();
    }
}
