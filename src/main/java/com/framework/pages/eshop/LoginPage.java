package com.framework.pages.eshop;

import com.framework.pages.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By USER_EMAIL_INPUT = By.id("userEmail");
    private final By PASSWORD_INPUT = By.id("userPassword");
    private final By LOGIN_BUTTON = By.id("login");
    private final By EMAIL_FORMAT_CHECK_MESSAGE_DIV= By.xpath("//div[text()='*Enter Valid Email']");
    private final By EMAIL_REQUIRED_CHECK_MESSAGE_DIV= By.xpath("//div[text()='*Email is required']");
    private final By PASSWORD_REQUIRED_CHECK_MESSAGE_DIV= By.xpath("//div[text()='*Password is required']");



    public LoginPage enterUserEmail(String userEmail) {
        sendKeys(USER_EMAIL_INPUT, userEmail);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(PASSWORD_INPUT, password);
        return this;
    }

    public ShoppingPage clickLogin() {
        click(LOGIN_BUTTON);
        return new ShoppingPage();
    }

    /** 一步完成登入 */
    public ShoppingPage login(String username, String password) {
        return enterUserEmail(username)
                .enterPassword(password)
                .clickLogin();
    }

    /**
     * 回傳Email必填訊息
     */
    public String getEmailRequiredMessage(){
        return getText(EMAIL_REQUIRED_CHECK_MESSAGE_DIV);
    }

    /**
     * 回傳Password必填訊息
     */
    public String getPasswordRequiredMessage(){
        return getText(PASSWORD_REQUIRED_CHECK_MESSAGE_DIV);
    }

    /**
     * 回傳Email格式錯誤訊息
     */
    public String getEmailFormatMessage(){
        return getText(EMAIL_FORMAT_CHECK_MESSAGE_DIV);
    }
}
