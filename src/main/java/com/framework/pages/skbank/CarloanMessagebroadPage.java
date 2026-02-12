package com.framework.pages.skbank;



import com.framework.pages.BasePage;
import org.openqa.selenium.By;

public class CarloanMessagebroadPage extends BasePage {

    private final By LOAN_TITTLE = By.xpath("//a[normalize-space()='車貸試算']");

    private final By NAME_INPUT = By.id("nameControl");
    private final By PHONE_INPUT = By.id("phoneControl");
    private final By CITY_SELECT = By.xpath("//span[text()='指定分行縣市']");
    private final By CITY_OPTION = By.xpath("//span[text()='台北市']");
    private final By DISTRICT_SELECT = By.xpath("//span[text()='指定分行鄉鎮市區']");
    private final By DISTRICT_OPTION = By.xpath("//span[text()='中正區']");
    private final By BRANCH_SELECT = By.xpath("//span[text()='選擇分行']");
    private final By BRANCH_OPTION = By.xpath("//span[text()='中正分行']");
    private final By PRIVACY_CHECKBOX = By.xpath("//div[@class='consulation-request__block']//div[@class='checkbox__icon-box']");


}


