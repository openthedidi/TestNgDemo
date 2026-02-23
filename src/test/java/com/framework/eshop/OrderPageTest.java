package com.framework.eshop;

import com.framework.base.BaseTest;
import com.framework.pages.eshop.*;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class OrderPageTest extends BaseTest {


    @Test(groups = {"smoke", "regression", "end2End"})
    public void validateOrderProduct() {
        getDriver().get("https://rahulshettyacademy.com/client/#/auth/login");
        LoginPage loginPage = new LoginPage();
        ShoppingPage shoppingPage = loginPage.login("neux@gmail.com", "1q@W3e$R5t");
        shoppingPage.addCoatToCart();
        CartPage cartPage = shoppingPage.goToCart();
        OrderPage orderPage = cartPage.checkout();
        orderPage.inputCountry("India");
        orderPage.inputCreditCardNumber("4111111111111111");
        orderPage.inputCVV("455");
        orderPage.inputMail("cathyl1u123425@gmail.com");
        orderPage.inputNameOnCard("Cathyl");
        OrderSuccessPage orderSuccessPage = orderPage.placeOrder();
        AssertJUnit.assertEquals("THANKYOU FOR THE ORDER.",orderSuccessPage.getSuccessAddCartString());


    }

    @DataProvider(name = "orderDataByArray")
    public Iterator<Map<String,String>> getData() {
        Map<String,String> maps=new HashMap<>();
        maps.put("country","United States");
        return Collections.singleton(maps).iterator();
    }



}
