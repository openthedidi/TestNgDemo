package com.framework.skbank;

import com.framework.base.BaseTest;
import com.framework.enums.BrowserType;
import com.framework.pages.skbank.CarLoanMessageBroadPage;
import com.framework.pages.skbank.SkBankSitemapPage;
import org.testng.annotations.Test;

public class CarLoanMessageBroadPageTest extends BaseTest {
    @Override
    protected BrowserType getBrowserType() {
        return BrowserType.CHROME;
    }


    @Test
    public void goToSiteMapPage(){
        getDriver().get("https://www.skbank.com.tw/carloan_messagebroad/");
        CarLoanMessageBroadPage carLoanMessageBroadPage = new CarLoanMessageBroadPage();
        SkBankSitemapPage skBankSitemapPage = carLoanMessageBroadPage.goToSitemapPage();
        assert "網站導覽".equals(skBankSitemapPage.getSiteMapTitle());

    }

}
