package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UrlReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream input = new FileInputStream("src/main/resources/urlconfig.properties");
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load URL Configuration file.", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
