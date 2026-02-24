package com.framework.skbank;

import com.framework.base.BaseTest;
import com.framework.enums.BrowserType;
import com.framework.pages.skbank.CarLoanMessageBroadPage;
import com.framework.pages.skbank.SkBankSitemapPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class CarLoanMessageBroadPageTest extends BaseTest {
    @Override
    protected BrowserType getBrowserType() {
        return BrowserType.CHROME;
    }


    @Test(groups = {"smoke"})
    public void goToSiteMapPage(){
        getDriver().get("https://www.skbank.com.tw/carloan_messagebroad/");
        CarLoanMessageBroadPage carLoanMessageBroadPage = new CarLoanMessageBroadPage();
        SkBankSitemapPage skBankSitemapPage = carLoanMessageBroadPage.goToSitemapPage();
        assert "網站導覽".equals(skBankSitemapPage.getSiteMapTitle());

    }

    @Test(groups = {"smoke"})
    public void emptySubmit(){
        getDriver().get("https://www.skbank.com.tw/carloan_messagebroad/");
        CarLoanMessageBroadPage carLoanMessageBroadPage = new CarLoanMessageBroadPage();
        Map<String, String> errorMessageMaps = carLoanMessageBroadPage.emptySubmit();
        Assert.assertEquals(errorMessageMaps.get("name"), "此欄位必填");
        Assert.assertEquals(errorMessageMaps.get("phone"), "此欄位必填");
        Assert.assertEquals(errorMessageMaps.get("city"), "此欄位必填");
        Assert.assertEquals(errorMessageMaps.get("district"), "此欄位必填");
        Assert.assertEquals(errorMessageMaps.get("branch"), "此欄位必填");
    }


    @Test(groups = {"smoke", "regression"})
    public void errorNameFormat(){
        getDriver().get("https://www.skbank.com.tw/carloan_messagebroad/");
        CarLoanMessageBroadPage carLoanMessageBroadPage = new CarLoanMessageBroadPage();
        String errorMessage = carLoanMessageBroadPage.errorNameFormat("1");
        Assert.assertEquals(errorMessage, "此欄位格式錯誤");
    }

    @Test(groups = {"smoke", "regression"})
    public void errorPhoneFormat(){
        getDriver().get("https://www.skbank.com.tw/carloan_messagebroad/");
        CarLoanMessageBroadPage carLoanMessageBroadPage = new CarLoanMessageBroadPage();
        String errorMessage = carLoanMessageBroadPage.errorPhoneFormat("123456789");
        Assert.assertEquals(errorMessage, "此欄位格式錯誤");

    }

    @Test(groups = {"smoke", "regression"})
    public void getDistrictOptionNames(){
        getDriver().get("https://www.skbank.com.tw/carloan_messagebroad/");
        CarLoanMessageBroadPage carLoanMessageBroadPage = new CarLoanMessageBroadPage();
        String cityName = "台北市";
        List<String> optionNames = carLoanMessageBroadPage.getDistrictOptionNames(cityName)
                .stream().map(String::trim)
                //把"指定分行鄉鎮市區"移除
                .filter(name -> !name.equals("指定分行鄉鎮市區")).toList();
        Assert.assertTrue(optionNames.contains("中正區"));
        Assert.assertEquals(optionNames.size(), 12);

    }

    @Test(groups = {"smoke", "regression"})
    public void getBranchOptionNames(){
        getDriver().get("https://www.skbank.com.tw/carloan_messagebroad/");
        CarLoanMessageBroadPage carLoanMessageBroadPage = new CarLoanMessageBroadPage();
        String districtName = "中正區";
        String cityName = "台北市";
        List<String> optionNames = carLoanMessageBroadPage.getBranchOptionNames(districtName, cityName)
                .stream().map(String::trim)
                .filter(name -> !name.equals("選擇分行")).toList();
        Assert.assertTrue(optionNames.contains("中正分行"));
        Assert.assertEquals(optionNames.size(), 2);
    }



}
