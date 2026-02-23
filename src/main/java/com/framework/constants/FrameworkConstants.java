package com.framework.constants;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    // Config paths
    public static final String CONFIG_PROPERTIES_PATH = "src/main/resources/config/config.properties";
    public static final String CONFIG_DIR = "src/main/resources/config/";

    // Report paths
    public static final String EXTENT_REPORT_DIR = "reports/extent-reports/";
    public static final String SCREENSHOT_DIR = "reports/screenshots/";
    public static final String FAILED_SCREENSHOT_DIR = SCREENSHOT_DIR + "failed/";

    // Log paths
    public static final String LOG_DIR = "logs/";

    // Property keys
    public static final String BROWSER_KEY = "browser";
    public static final String ENVIRONMENT_KEY = "environment";
    public static final String HEADLESS_KEY = "headless";
    public static final String BASE_URL_KEY = "base.url";

    // Test data paths
    public static final String TESTDATA_DIR = "src/test/resources/testdata/";
    public static final String DB_PROPERTIES_PATH = "src/main/resources/config/db.properties";

    // Defaults
    public static final String DEFAULT_BROWSER = "CHROME";
    public static final String DEFAULT_ENVIRONMENT = "DEV";
    public static final boolean DEFAULT_HEADLESS = false;
}
