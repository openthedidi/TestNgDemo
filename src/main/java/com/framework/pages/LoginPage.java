package com.framework.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By USER_NAME_INPUT = By.id("userEmail");
    private final By PASSWORD_INPUT = By.id("userPassword");
    private final By LOGIN_BUTTON = By.id("login");

    public LoginPage enterUserName(String username) {
        sendKeys(USER_NAME_INPUT, username);
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
        return enterUserName(username)
                .enterPassword(password)
                .clickLogin();
    }
}
