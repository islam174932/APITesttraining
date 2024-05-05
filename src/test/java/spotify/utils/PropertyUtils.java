package spotify.utils;


import java.io.IOException;
import java.util.Properties;

import java.io.BufferedReader;
import java.io.FileReader;

public class PropertyUtils {

    public static Properties readProperties(String filePath) {
        Properties properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + filePath, e);
        }
        return properties;
    }
}

