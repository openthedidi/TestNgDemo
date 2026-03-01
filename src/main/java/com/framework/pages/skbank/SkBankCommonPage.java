package com.framework.pages.skbank;

import com.framework.enums.WaitStrategy;
import com.framework.pages.BasePage;
import org.openqa.selenium.By;

/**
 * 負責天地的元件
 */
public class SkBankCommonPage extends BasePage {

    private final By SITE_MAP_BTN = By.xpath("//a[text()='網站導覽']");


    public SkBankSitemapPage goToSitemapPage(){
        click(SITE_MAP_BTN, WaitStrategy.CLICKABLE);
        return new SkBankSitemapPage();
    }


}
