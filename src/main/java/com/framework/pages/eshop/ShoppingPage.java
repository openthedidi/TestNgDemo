package com.framework.pages.eshop;

import org.openqa.selenium.By;

public class ShoppingPage extends EshopCommonPage {
    // Locators for elements on the Shopping Page
    private final By SHOES_ADD_CART_BUTTON = By.xpath("//b[text()='ADIDAS ORIGINAL']/ancestor::div[@class='card-body']/descendant::button[@class='btn w-10 rounded']");

    private final By CART_DETAIL_BUTTON = By.xpath("//button[@routerlink='/dashboard/cart']");

    private final By SHOP_PAGE_TITLE = By.xpath("//h3[text()='Automation']");


    /**
     * 登入成功到ShoppingPage的證據
     *
     * @return SHOP_PAGE_TITLE的text
     */
    public String getLoginSucessString() {
        return getText(SHOP_PAGE_TITLE);

    }


}
