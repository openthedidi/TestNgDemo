package com.framework.pages.eshop;

import com.framework.pages.BasePage;
import org.openqa.selenium.By;

/**
 * 負責天地的元件
 */
public class EshopCommonPage extends BasePage {

    private final By LOGOUT_BUTTON=By.xpath("//button[@class='btn btn-custom' and text()=' Sign Out ']");


    /**
     * 登出
     */
    public LoginPage logout(){
        click(LOGOUT_BUTTON);
        return new LoginPage();
    }

}
