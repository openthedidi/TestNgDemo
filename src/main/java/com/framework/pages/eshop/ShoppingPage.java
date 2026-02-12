package com.framework.pages.eshop;

import com.framework.pages.BasePage;
import org.openqa.selenium.By;

public class ShoppingPage extends BasePage {
    // Locators for elements on the Shopping Page
    private final By SHOES_ADD_CART_BUTTON = By.xpath("//b[text()='ADIDAS ORIGINAL']/ancestor::div[@class='card-body']/descendant::button[@class='btn w-10 rounded']");

    private final By CART_DETAIL_BUTTON = By.xpath("//button[@routerlink='/dashboard/cart']");

    private final By SUCCESS_MESSAGE_DIV = By.xpath("//div[@aria-label='Product Added To Cart']");

    private final By LOGOUT_BUTTON=By.xpath("//button[@class='btn btn-custom' and text()=' Sign Out ']");


    /**
     * 登入成功到ShoppingPage的證據
     * @return CART_DETAIL_BUTTON的text
     */
    public String getLoginSucessString(){
        return getText(SUCCESS_MESSAGE_DIV);

    }




}
