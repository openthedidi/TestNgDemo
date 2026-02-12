package com.framework.eshop;

import com.framework.base.BaseTest;
import com.framework.enums.BrowserType;
import com.framework.pages.eshop.LoginPage;
import com.framework.pages.eshop.ShoppingPage;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Override
    protected BrowserType getBrowserType() {
        return BrowserType.CHROME;
    }

    @Override
    protected boolean isHeadless() {
        return true;
    }


    /**
     * 先測試happyPath 的login End2End
     */
    @Test(enabled = false)
    public void validateHappyPathLogin() {

        getDriver().get("https://rahulshettyacademy.com/client/#/auth/login");
        LoginPage loginPage = new LoginPage();
        ShoppingPage shoppingPag = loginPage.login("neux@gmail.com", "1q@W3e$R5t");
        assert shoppingPag.getLoginSucessString().contains("Success");

    }


    /**
     * 測試email input驗證
     */
    @Test
    public void validateEmailInput() {
        getDriver().get("https://rahulshettyacademy.com/client/#/auth/login");
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserEmail("1213");
        loginPage.clickLogin();
        assert loginPage.getEmailFormatMessage().contains("Enter Valid Email");

    }

    /**
     * 測試email及password 必填驗證
     */
    @Test
    public void validatePasswordInput() {
        getDriver().get("https://rahulshettyacademy.com/client/#/auth/login");
        LoginPage loginPage = new LoginPage();
        loginPage.clickLogin();
        assert loginPage.getEmailRequiredMessage().contains("Email is required");
        assert loginPage.getPasswordRequiredMessage().contains("Password is required");
    }

}
