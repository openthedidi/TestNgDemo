package com.framework.pages.eshop;

import com.framework.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class OrderPage extends EshopCommonPage {
    private final By CREDIT_CARD_NUMBER_INPUT = By.xpath("//div[text()='Credit Card Number ']/following-sibling::input");
    private final By EXPIRY_DATE_MONTH_SELECT = By.xpath("//div[text()='Expiry Date ']/following-sibling::select[1]");
    private final By EXPIRY_DATE_DATE_SELECT = By.xpath("//div[text()='Expiry Date ']/following-sibling::select[2]");
    private final By CVV_INPUT = By.xpath("//div[text()='CVV Code ']/following-sibling::input");
    private final By NAME_ON_CARD = By.xpath("//div[text()='Name on Card ']/following-sibling::input");
    private final By SHOPPING_INFO_MAIL_INPUT = By.xpath("//div[@class='user__name mt-5']/child::input");
    private final By SHOPPING_INFO_COUNTRY_INPUT = By.xpath("//div[@class='user__address']/descendant::input");
    private final By PLACE_ORDER_BTN = By.xpath("//a[text()='Place Order ']");
    private final By COUNTRY_DROPDOWN_OPTIONS = By.xpath("//section[@class='ta-results list-group ng-star-inserted']//span");


    public void inputCountry(String country) {
        sendKeys(SHOPPING_INFO_COUNTRY_INPUT, country);
        waitForElement(COUNTRY_DROPDOWN_OPTIONS, WaitStrategy.VISIBLE);
        click(COUNTRY_DROPDOWN_OPTIONS);
    }

    public void selectExpiryMonth(String month) {
        Select monthSelect = new Select(waitForElement(EXPIRY_DATE_MONTH_SELECT, WaitStrategy.PRESENT));
        monthSelect.selectByVisibleText(month);
    }


    public void selectExpiryDate(String date) {
        Select dateSelect = new Select(waitForElement(EXPIRY_DATE_DATE_SELECT, WaitStrategy.PRESENT));
        dateSelect.selectByVisibleText(date);
    }

    public void inputCreditCardNumber(String creditCardNumber) {
        sendKeys(CREDIT_CARD_NUMBER_INPUT, creditCardNumber);
    }

    public void inputCVV(String cvv) {
        sendKeys(CVV_INPUT, cvv);
    }

    public void inputNameOnCard(String nameOnCard) {
        sendKeys(NAME_ON_CARD, nameOnCard);
    }

    public void inputMail(String mail){
        sendKeys(SHOPPING_INFO_MAIL_INPUT, mail);

    }

    public OrderSuccessPage placeOrder() {
        click(PLACE_ORDER_BTN);
        return new OrderSuccessPage();
    }


}
