package com.framework.config;

import com.framework.constants.FrameworkConstants;
import com.framework.exceptions.PropertyFileNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * Singleton ConfigManager.
 * Priority: System Property > env.properties > config.properties
 */
public final class ConfigManager {

    private static ConfigManager instance;
    private final Map<String, String> configMap = new HashMap<>();

    /**
     * 讀取config.properties
     */
    private ConfigManager() {
        loadProperties(FrameworkConstants.CONFIG_PROPERTIES_PATH);
        loadEnvironmentProperties();
    }

    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
            properties.forEach((key, value) -> configMap.put(String.valueOf(key), String.valueOf(value).trim()));
        } catch (IOException e) {
            throw new PropertyFileNotFoundException("Property file not found: " + filePath, e);
        }
    }

    /**
     * 讀取每個環境的.properties
     */
    private void loadEnvironmentProperties() {
        String env = getProperty(FrameworkConstants.ENVIRONMENT_KEY, FrameworkConstants.DEFAULT_ENVIRONMENT);
        String envFilePath = FrameworkConstants.CONFIG_DIR + env.toLowerCase() + ".properties";
        try {
            loadProperties(envFilePath);
        } catch (PropertyFileNotFoundException e) {
            // Environment-specific file is optional; log warning silently
        }
    }

    /**
     * Get property with priority: System Property > loaded config
     */
    public String getProperty(String key) {
        String systemValue = System.getProperty(key);
        if (Objects.nonNull(systemValue) && !systemValue.isEmpty()) {
            return systemValue.trim();
        }
        return configMap.get(key);
    }

    /**
     * Get property with default fallback
     */
    public String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return Objects.nonNull(value) ? value : defaultValue;
    }

    public boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key);
        return Objects.nonNull(value) ? Boolean.parseBoolean(value) : defaultValue;
    }

    public int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key);
        if (Objects.nonNull(value)) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * Reset for testing purposes
     */
    static synchronized void reset() {
        instance = null;
    }
}
