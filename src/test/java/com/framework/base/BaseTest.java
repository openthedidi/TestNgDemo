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

    @Parameters({"browser", "headless"})
    @BeforeMethod
    public void setUp(@Optional BrowserType browser, @Optional("false") boolean headless) {
        LogUtils.info("Setting up WebDriver for thread: " + Thread.currentThread().getId());

        BrowserType browserType = browser != null ? browser : getBrowserType();
        boolean headlessFinal = headless || isHeadless();
        WebDriver driver = DriverFactory.createDriver(browserType, headlessFinal);
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

    /** 子類可覆寫，指定預設瀏覽器 */
    protected BrowserType getBrowserType() {
        return EnvironmentConfig.getBrowserType();
    }

    /** 子類可覆寫，指定是否 headless */
    protected boolean isHeadless() {
        return EnvironmentConfig.isHeadless();
    }
}
