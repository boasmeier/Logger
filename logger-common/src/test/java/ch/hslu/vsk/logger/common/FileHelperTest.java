package ch.hslu.vsk.logger.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileHelperTest {
    private static String filePath = "." + File.separator + "testConfig";

    @AfterEach
    void tearDown() {
        deleteConfigFile();
    }

    @Test
    void testRead() {
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
