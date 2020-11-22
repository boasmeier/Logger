package ch.hslu.vsk.logger.common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class FileHelper {

    private FileHelper() {
    }

    /**
     * Read configuration file and return the value of the specified argument names.
     *
     * @param path The path to the configuration files.
     * @param arguments The arguments to read in the file.
     * @return Returns a list containing all values of the given arguments.
     * @throws java.io.IOException
     */
    public static List<String> read(final String path, final List<String> arguments) throws IOException {
        try (FileReader reader = new FileReader(new File(path))) {
            Properties properties = new Properties();
            properties.load(reader);
            List<String> data = new ArrayList<>(arguments.size());
            arguments.forEach(a -> data.add(properties.getProperty(a)));
            return data;
        }
    }
}
