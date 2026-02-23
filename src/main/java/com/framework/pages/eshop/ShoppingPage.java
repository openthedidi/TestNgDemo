package com.framework.pages.eshop;

import org.openqa.selenium.By;

public class ShoppingPage extends EshopCommonPage {

    private final By SHOES_ADD_CART_BUTTON = By.xpath("//b[text()='ADIDAS ORIGINAL']/ancestor::div[@class='card-body']/descendant::button[@class='btn w-10 rounded']");

    private final By SHOES_ADD_COAT_BUTTON = By.xpath("//b[text()='ZARA COAT 3']/ancestor::div[@class='card-body']/descendant::button[@class='btn w-10 rounded']");

    private final By CART_DETAIL_BUTTON = By.xpath("//button[@routerlink='/dashboard/cart']");

    private final By SHOP_PAGE_TITLE = By.xpath("//h3[text()='Automation']");

    private final By SUCCESS_ADD_DIV = By.xpath("//div[@aria-label='Product Added To Cart']");


    /**
     * 登入成功到ShoppingPage的證據
     *
     * @return SHOP_PAGE_TITLE的text
     */
    public String getLoginSucessString() {
        return getText(SHOP_PAGE_TITLE);

    }

    /**
     * 成功加入購物車
     */
    public String getSuccessAddCartString(){
        return getText(SUCCESS_ADD_DIV);
    }

    /**
     * shoes加入購物車
     */
    public boolean addShoesToCart() {
        click(SHOES_ADD_CART_BUTTON);
        return isDisplayed(SUCCESS_ADD_DIV);

    }

    /**
     * coat加入購物車
     */
    public boolean addCoatToCart() {
        click(SHOES_ADD_COAT_BUTTON);
        return isDisplayed(SUCCESS_ADD_DIV);
    }

    /**
     * 進入購物車
     */
    public CartPage goToCart(){
        click(CART_DETAIL_BUTTON);
        return new CartPage();
    }




}
