package com.framework.pages.eshop;

import com.framework.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends EshopCommonPage {

    private final By PRODUCT_NAMES_IN_CART = By.xpath("//div[@class='cart']//h3");

    private final By CHECKOUT_BUTTON = By.xpath("//button[normalize-space()='Checkout']");

    public boolean isProductInCart(String productName) {
        return findElements(PRODUCT_NAMES_IN_CART).stream()
                .anyMatch(element -> element.getText().equalsIgnoreCase(productName));
    }

    public List<String> getProductNamesInCart() {
        return waitForElements(PRODUCT_NAMES_IN_CART, WaitStrategy.PRESENT)
                .stream().map(WebElement::getText).toList();

    }


    public OrderPage checkout() {
        click(CHECKOUT_BUTTON);
        return new OrderPage();
    }
}
