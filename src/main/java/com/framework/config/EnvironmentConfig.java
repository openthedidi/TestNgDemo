package com.framework.config;

import com.framework.constants.FrameworkConstants;
import com.framework.enums.BrowserType;
import com.framework.enums.EnvironmentType;

/**
 * Convenience wrapper around ConfigManager for common config access.
 */
public final class EnvironmentConfig {

    private EnvironmentConfig() {
    }

    public static String getBaseUrl() {
        return ConfigManager.getInstance().getProperty(FrameworkConstants.BASE_URL_KEY, "");
    }

    public static BrowserType getBrowserType() {
        String browser = ConfigManager.getInstance()
                .getProperty(FrameworkConstants.BROWSER_KEY, FrameworkConstants.DEFAULT_BROWSER);
        return BrowserType.fromString(browser);
    }

    public static EnvironmentType getEnvironmentType() {
        String env = ConfigManager.getInstance()
                .getProperty(FrameworkConstants.ENVIRONMENT_KEY, FrameworkConstants.DEFAULT_ENVIRONMENT);
        return EnvironmentType.fromString(env);
    }

    public static boolean isHeadless() {
        return ConfigManager.getInstance()
                .getBooleanProperty(FrameworkConstants.HEADLESS_KEY, FrameworkConstants.DEFAULT_HEADLESS);
    }
}
