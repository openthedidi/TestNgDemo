package com.framework.utils;

import com.framework.constants.FrameworkConstants;
import com.framework.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Screenshot capture utility.
 */
public final class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    /**
     * Captures a screenshot and saves to the failed screenshots directory.
     *
     * @param testName the test method name for the file name
     * @return the absolute path to the saved screenshot, or null on failure
     */
    public static String captureScreenshot(String testName) {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            LogUtils.warn("Cannot capture screenshot - driver is null");
            return null;
        }

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = testName + "_" + DateUtils.getTimestamp() + ".png";
            Path destDir = Path.of(FrameworkConstants.FAILED_SCREENSHOT_DIR);
            Files.createDirectories(destDir);
            Path destPath = destDir.resolve(fileName);
            Files.copy(srcFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);

            LogUtils.info("Screenshot saved: " + destPath.toAbsolutePath());
            return destPath.toAbsolutePath().toString();
        } catch (IOException e) {
            LogUtils.error("Failed to capture screenshot", e);
            return null;
        }
    }

    /**
     * Captures screenshot as Base64 for embedding in reports.
     */
    public static String captureScreenshotAsBase64() {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            return null;
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
