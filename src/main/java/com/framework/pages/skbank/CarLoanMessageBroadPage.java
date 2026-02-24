package com.framework.pages.skbank;


import com.framework.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarLoanMessageBroadPage extends SkBankCommonPage {

    private final By LOAN_TITTLE = By.xpath("//a[normalize-space()='車貸試算']");

    private final By NAME_INPUT = By.id("nameControl");
    private final By PHONE_INPUT = By.id("phoneControl");
    private final By CITY_SELECT = By.xpath("//span[text()='指定分行縣市']");

    private final By DISTRICT_SELECT = By.xpath("//span[text()='指定分行鄉鎮市區']");

    private final By DISTRICT_OPTIONS_LI = By.xpath("//span[text()='指定分行鄉鎮市區']/ancestor::ul/li");

    private final By BRANCH_SELECT = By.xpath("//span[text()='選擇分行']");

    private final By BRANCH_OPTION_LI = By.xpath("//span[text()='選擇分行']/ancestor::ul/li");

    private final By PRIVACY_CHECKBOX = By.xpath("//div[@class='consulation-request__block']//div[@class='checkbox__icon-box']");

    private final By SUBMIT_BUTTON = By.xpath("//div[contains(text(),'確認送出')]");

    private final By NAME_INPUT_REQUIRED_DIV = By.xpath("//div[text()='姓名']/ancestor::skbank-form-field/descendant::div[text()=' 此欄位必填 ']");

    private final By NAME_INPUT_ERROR_DIV = By.xpath("//div[text()='姓名']/ancestor::skbank-form-field/descendant::div[text()=' 此欄位格式錯誤 ']");

    private final By PHONE_INPUT_REQUIRED_DIV = By.xpath("//div[text()='行動電話']/ancestor::skbank-form-field/descendant::div[text()=' 此欄位必填 ']");

    private final By PHONE_INPUT_ERROR_DIV = By.xpath("//div[text()='行動電話']/ancestor::skbank-form-field/descendant::div[text()=' 此欄位格式錯誤 ']");

    private final By CITY_SELECT_REQUIRED_DIV = By.xpath("//div[@class='consulation-request-equal-3']//div[1]//skbank-error[1]//div[1]//div[2]");

    private final By DISTRICT_SELECT_REQUIRED_DIV = By.xpath("//div[@class='form-field__field']//div[2]//skbank-error[1]//div[1]//div[2]");

    private final By BRANCH_SELECT_REQUIRED_DIV = By.xpath("//div[@class='consulation-request__block']//div[3]//skbank-error[1]//div[1]//div[2]");


    public Map<String, String> emptySubmit() {
        click(SUBMIT_BUTTON);
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("name", getText(NAME_INPUT_REQUIRED_DIV));
        errorMap.put("phone", getText(PHONE_INPUT_REQUIRED_DIV));
        errorMap.put("city", getText(CITY_SELECT_REQUIRED_DIV));
        errorMap.put("district", getText(DISTRICT_SELECT_REQUIRED_DIV));
        errorMap.put("branch", getText(BRANCH_SELECT_REQUIRED_DIV));
        return errorMap;
    }

    public String errorNameFormat(String Name) {
        sendKeys(NAME_INPUT, Name);
        return getText(NAME_INPUT_ERROR_DIV);
    }

    public String errorPhoneFormat(String Phone) {
        sendKeys(PHONE_INPUT, Phone);
        return getText(PHONE_INPUT_ERROR_DIV);

    }

    public List<String> getDistrictOptionNames(String cityName) {
        click(CITY_SELECT);
        click(By.xpath("//span[text()='" + cityName + "']"));
        click(DISTRICT_SELECT);
        return waitForElements(DISTRICT_OPTIONS_LI, WaitStrategy.PRESENT)
                .stream().map(WebElement::getText).toList();
    }


    public List<String> getBranchOptionNames(String districtName, String CityName) {
        click(CITY_SELECT);
        click(By.xpath("//span[text()='" + CityName + "']"));
        click(DISTRICT_SELECT);
        click(By.xpath("//span[text()='" + districtName + "']"));
        click(BRANCH_SELECT);
        return waitForElements(BRANCH_OPTION_LI, WaitStrategy.PRESENT)
                .stream().map(WebElement::getText).toList();
    }


}


