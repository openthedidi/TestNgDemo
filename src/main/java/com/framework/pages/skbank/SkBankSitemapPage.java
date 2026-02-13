package com.framework.pages.skbank;

import org.openqa.selenium.By;

public class SkBankSitemapPage extends SkBankCommonPage{

    private final By TITLE_P = By.xpath("//p[text()='網站導覽']");


    public String getSiteMapTitle(){
        return getText(TITLE_P);
    }

}
