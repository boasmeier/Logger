package ch.hslu.vsk.logger.common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileHelper {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(FileHelper.class.getName());

    /**
     * Read configuration file and return the value of the specified argument names.
     * @param path The path to the configuration files.
     * @param arguments The arguments to read in the file.
     * @return Returns a list containing all values of the given arguments.
     */
    public static List<String> read(String path, List<String> arguments) {
        try (FileReader reader = new FileReader(new File(path))) {
            Properties properties = new Properties();
            properties.load(reader);
            List<String> data = new ArrayList<String>(arguments.size());
            arguments.forEach(a -> data.add(properties.getProperty(a)));
            return data;
        } catch (IOException e) {
            LOGGER.severe("An error occurred while reading config file: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
