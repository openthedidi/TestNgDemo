package com.framework.eshop;

import com.framework.base.BaseTest;
import com.framework.pages.eshop.CartPage;
import com.framework.pages.eshop.LoginPage;
import com.framework.pages.eshop.ShoppingPage;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.List;

public class ShoppingPageTest extends BaseTest {

    @Test(groups = {"smoke", "regression", "end2End"})
    public void Add2Cart() {
        ShoppingPage shoppingPag = Login();
        AssertJUnit.assertTrue(shoppingPag.addShoesToCart());

    }

    @Test(groups = {"smoke", "regression", "end2End"})
    public void examCartItems() {
        ShoppingPage shoppingPag = Login();
        shoppingPag.addShoesToCart();
        CartPage cartPage = shoppingPag.goToCart();
        List<String> itemNames = cartPage.getProductNamesInCart();
        AssertJUnit.assertEquals(1, itemNames.size());
        AssertJUnit.assertTrue(cartPage.isProductInCart("ADIDAS ORIGINAL"));

    }


    private ShoppingPage Login(){
        getDriver().get("https://rahulshettyacademy.com/client/#/auth/login");
        LoginPage loginPage = new LoginPage();
        return loginPage.login("neux@gmail.com", "1q@W3e$R5t");
    }


}
