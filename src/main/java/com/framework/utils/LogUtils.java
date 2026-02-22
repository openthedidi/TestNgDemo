package com.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Dual logging: Log4j2 + ExtentReport (via ThreadLocal).
 */
public final class LogUtils {

    private static final Logger logger = LogManager.getLogger(LogUtils.class);

    /** ThreadLocal to hold ExtentTest log callback */
    private static final ThreadLocal<java.util.function.Consumer<String>> extentLogger = new ThreadLocal<>();

    private LogUtils() {
    }

    public static void setExtentLogger(java.util.function.Consumer<String> logFunction) {
        extentLogger.set(logFunction);
    }

    public static void removeExtentLogger() {
        extentLogger.remove();
    }

    public static void info(String message) {
        logger.info(message);
        logToExtent(message);
    }

    public static void warn(String message) {
        logger.warn(message);
        logToExtent("[WARN] " + message);
    }

    public static void error(String message) {
        logger.error(message);
        logToExtent("[ERROR] " + message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
        logToExtent("[ERROR] " + message + " - " + throwable.getMessage());
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    /**
     * Log a test step — writes to both Log4j2 and ExtentReport.
     */
    public static void step(String stepDescription) {
        logger.info("[STEP] " + stepDescription);
        logToExtent("<b>[STEP]</b> " + stepDescription);
    }

    private static void logToExtent(String message) {
        var consumer = extentLogger.get();
        if (consumer != null) {
            consumer.accept(message);
        }
    }
}
