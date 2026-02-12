package com.framework.base;

import com.framework.config.EnvironmentConfig;
import com.framework.driver.DriverFactory;
import com.framework.driver.DriverManager;
import com.framework.enums.BrowserType;
import com.framework.utils.LogUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Base class for all framework test classes.
 * Manages WebDriver lifecycle per test method.
 *
 * Browser priority: TestNG XML @Parameters > System Property > config.properties
 */
public abstract class BaseTest {

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional BrowserType browser) {
        LogUtils.info("Setting up WebDriver for thread: " + Thread.currentThread().getId());

        BrowserType browserType = browser != null ? browser : EnvironmentConfig.getBrowserType();
        boolean headless = EnvironmentConfig.isHeadless();
        WebDriver driver = DriverFactory.createDriver(browserType, headless);
        DriverManager.setDriver(driver);
        LogUtils.info("WebDriver [" + browserType + "] initialized for thread: "
                + Thread.currentThread().getId());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        LogUtils.info("Tearing down WebDriver for thread: " + Thread.currentThread().getId());
        DriverManager.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
