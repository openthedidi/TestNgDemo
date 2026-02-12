package com.framework.driver;

import com.framework.config.EnvironmentConfig;
import com.framework.constants.TimeoutConstants;
import com.framework.enums.BrowserType;
import com.framework.exceptions.BrowserNotSupportedException;
import com.framework.utils.LogUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * Factory to create WebDriver instances based on BrowserType.
 */
public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver() {
        BrowserType browserType = EnvironmentConfig.getBrowserType();
        boolean headless = EnvironmentConfig.isHeadless();
        return createDriver(browserType, headless);
    }

    public static WebDriver createDriver(BrowserType browserType, boolean headless) {
        LogUtils.info("Creating " + browserType + " driver (headless=" + headless + ")");
        WebDriver driver = switch (browserType) {
            case CHROME -> createChromeDriver(headless);
            case EDGE -> createEdgeDriver(headless);
            case FIREFOX -> createFirefoxDriver(headless);
        };
        configureDriver(driver);
        return driver;
    }

    private static WebDriver createChromeDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    private static WebDriver createEdgeDriver(boolean headless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        return new EdgeDriver(options);
    }

    private static WebDriver createFirefoxDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        return new FirefoxDriver(options);
    }

    private static void configureDriver(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(TimeoutConstants.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().scriptTimeout(
                Duration.ofSeconds(TimeoutConstants.SCRIPT_TIMEOUT));
        driver.manage().window().maximize();
    }
}
