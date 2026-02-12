package com.framework.utils;

import com.framework.constants.TimeoutConstants;
import com.framework.driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Advanced wait utilities for page load and JavaScript readiness.
 */
public final class WaitUtils {

    private WaitUtils() {
    }

    public static void waitForPageLoad() {
        waitForPageLoad(TimeoutConstants.PAGE_LOAD_TIMEOUT);
    }

    public static void waitForPageLoad(int timeoutSeconds) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until((ExpectedCondition<Boolean>) d -> {
            String readyState = ((JavascriptExecutor) d)
                    .executeScript("return document.readyState").toString();
            return "complete".equals(readyState);
        });
    }

    public static void waitForJQuery() {
        waitForJQuery(TimeoutConstants.PAGE_LOAD_TIMEOUT);
    }

    public static void waitForJQuery(int timeoutSeconds) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until((ExpectedCondition<Boolean>) d -> {
            JavascriptExecutor js = (JavascriptExecutor) d;
            Boolean jQueryDefined = (Boolean) js.executeScript(
                    "return typeof jQuery !== 'undefined'");
            if (Boolean.TRUE.equals(jQueryDefined)) {
                return (Boolean) js.executeScript("return jQuery.active == 0");
            }
            return true;
        });
    }

    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LogUtils.warn("Sleep interrupted");
        }
    }
}
