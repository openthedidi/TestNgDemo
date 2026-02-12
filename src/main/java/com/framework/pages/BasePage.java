package com.framework.pages;

import com.framework.constants.TimeoutConstants;
import com.framework.driver.DriverManager;
import com.framework.enums.WaitStrategy;
import com.framework.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Base class for all Page Objects.
 * Provides common UI operations with built-in waits.
 */
public abstract class BasePage {

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    protected WebElement waitForElement(By locator, WaitStrategy waitStrategy) {
        WebDriverWait wait = new WebDriverWait(getDriver(),
                Duration.ofSeconds(TimeoutConstants.EXPLICIT_WAIT));
        return switch (waitStrategy) {
            case CLICKABLE -> wait.until(ExpectedConditions.elementToBeClickable(locator));
            case VISIBLE -> wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            case PRESENT -> wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            case NONE -> getDriver().findElement(locator);
        };
    }

    protected void click(By locator) {
        click(locator, WaitStrategy.CLICKABLE);
    }

    protected void click(By locator, WaitStrategy waitStrategy) {
        WebElement element = waitForElement(locator, waitStrategy);
        LogUtils.step("Click on element: " + locator);
        element.click();
    }

    protected void sendKeys(By locator, String text) {
        sendKeys(locator, text, WaitStrategy.VISIBLE);
    }

    protected void sendKeys(By locator, String text, WaitStrategy waitStrategy) {
        WebElement element = waitForElement(locator, waitStrategy);
        element.clear();
        LogUtils.step("Enter text '" + text + "' into element: " + locator);
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return getText(locator, WaitStrategy.VISIBLE);
    }

    protected String getText(By locator, WaitStrategy waitStrategy) {
        WebElement element = waitForElement(locator, waitStrategy);
        return element.getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return waitForElement(locator, WaitStrategy.VISIBLE).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected List<WebElement> findElements(By locator) {
        return getDriver().findElements(locator);
    }

    protected String getTitle() {
        return getDriver().getTitle();
    }
}
