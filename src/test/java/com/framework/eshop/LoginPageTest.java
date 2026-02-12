package com.framework.eshop;

import com.framework.base.BaseTest;
import com.framework.config.EnvironmentConfig;
import com.framework.pages.LoginPage;
import com.framework.pages.ShoppingPage;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    /**
     * 先測試happyPath 的login End2End
     */
    @Test
    public void validateHappyPathLogin() {
        getDriver().get(EnvironmentConfig.getBaseUrl());
        LoginPage loginPage = new LoginPage();
        ShoppingPage shoppingPag = loginPage.login("neux@gmail.com", "1q@W3e$R5t");





    }

}
