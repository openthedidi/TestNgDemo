package com.framework.pages.eshop;

import org.openqa.selenium.By;

public class OrderSuccessPage extends EshopCommonPage {
    private final By SUCCESS_MESSAGE_H1 = By.xpath("//h1[@class='hero-primary']");
    private final By ORDER_ITEMS_TITLE = By.xpath("//div[@class='ng-star-inserted']//td[@class='line-item product-info-column m-3']//div[@class='title']");

    public String getSuccessAddCartString() {
        return getText(SUCCESS_MESSAGE_H1);
    }


}
