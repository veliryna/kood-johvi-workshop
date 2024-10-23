package com.automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();

    static {
        // Load all the property files
        loadPropertiesFile("src/main/resources/config.properties");
        loadPropertiesFile("src/main/resources/element-locators.properties");
        loadPropertiesFile("src/main/resources/error-message.properties");
    }

    // Method to load individual properties files
    private static void loadPropertiesFile(String filePath) {
        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);
            logger.info("Loaded properties from file: {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to load properties file: {}", filePath, e); // Logging the error
        }
    }

    // Method to get a property by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
