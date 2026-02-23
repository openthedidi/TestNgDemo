package com.framework.eshop;

import com.framework.base.BaseTest;
import com.framework.constants.FrameworkConstants;
import com.framework.pages.eshop.*;
import com.framework.utils.DatabaseUtils;
import com.framework.utils.ExcelDataUtils;
import com.framework.utils.JsonDataUtils;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class OrderPageTest extends BaseTest {

    @Override
    protected boolean isHeadless() {
        return true;
    }

    @Test(groups = {"smoke", "regression", "end2End"})
    public void validateOrderProduct() {
        OrderPage orderPage = jump2OrderPage();
        orderPage.inputCountry("India");
        orderPage.inputCreditCardNumber("4111111111111111");
        orderPage.inputCVV("455");
        orderPage.inputMail("cathyl1u123425@gmail.com");
        orderPage.inputNameOnCard("Cathyl");
        OrderSuccessPage orderSuccessPage = orderPage.placeOrder();
        AssertJUnit.assertEquals("THANKYOU FOR THE ORDER.", orderSuccessPage.getSuccessAddCartString());
    }

    @Test(groups = {"smoke", "regression", "end2End"}, dataProvider = "orderDataByMap")
    public void validateOrderProductByDataProvider(HashMap<String, String> data) {
        OrderPage orderPage = jump2OrderPage();
        orderPage.inputCountry(data.get("country"));
        orderPage.inputCreditCardNumber(data.get("creditCardNumber"));
        orderPage.inputCVV(data.get("cvv"));
        orderPage.inputMail(data.get("mail"));
        orderPage.inputNameOnCard(data.get("nameOnCard"));
        OrderSuccessPage orderSuccessPage = orderPage.placeOrder();
        AssertJUnit.assertEquals("THANKYOU FOR THE ORDER.", orderSuccessPage.getSuccessAddCartString());

    }


    @Test(groups = {"smoke", "regression", "end2End"}, dataProvider = "orderDataByJson")
    public void validateOrderProductByJson(HashMap<String, String> data) {
        OrderPage orderPage = jump2OrderPage();
        orderPage.inputCountry(data.get("country"));
        orderPage.inputCreditCardNumber(data.get("creditCardNumber"));
        orderPage.inputCVV(data.get("cvv"));
        orderPage.inputMail(data.get("mail"));
        orderPage.inputNameOnCard(data.get("nameOnCard"));
        OrderSuccessPage orderSuccessPage = orderPage.placeOrder();
        AssertJUnit.assertEquals("THANKYOU FOR THE ORDER.", orderSuccessPage.getSuccessAddCartString());
    }

    @Test(groups = {"regression", "end2End"}, dataProvider = "orderDataByExcel")
    public void validateOrderProductByExcel(HashMap<String, String> data) {
        OrderPage orderPage = jump2OrderPage();
        orderPage.inputCountry(data.get("country"));
        orderPage.inputCreditCardNumber(data.get("creditCardNumber"));
        orderPage.inputCVV(data.get("cvv"));
        orderPage.inputMail(data.get("mail"));
        orderPage.inputNameOnCard(data.get("nameOnCard"));
        OrderSuccessPage orderSuccessPage = orderPage.placeOrder();
        AssertJUnit.assertEquals("THANKYOU FOR THE ORDER.", orderSuccessPage.getSuccessAddCartString());
    }

    @Test(groups = {"regression", "end2End", "db"}, dataProvider = "orderDataByMySql")
    public void validateOrderProductByMySql(HashMap<String, String> data) {
        OrderPage orderPage = jump2OrderPage();
        orderPage.inputCountry("India");
        orderPage.inputCreditCardNumber(data.get("creditCardNumber"));
        orderPage.inputCVV(data.get("cvv"));
        orderPage.inputMail(data.get("mail"));
        orderPage.inputNameOnCard("TomTest");
        OrderSuccessPage orderSuccessPage = orderPage.placeOrder();
        AssertJUnit.assertEquals("THANKYOU FOR THE ORDER.", orderSuccessPage.getSuccessAddCartString());
    }

    @DataProvider(name = "orderDataByMap")
    public Object[][] orderDataByMap() {
        Map<String, String> data1 = new HashMap<>();
        data1.put("country", "India");
        data1.put("creditCardNumber", "4111111111111100");
        data1.put("cvv", "666");
        data1.put("nameOnCard", "Tom");
        data1.put("mail", "Tom1u123425@gmail.com");

        Map<String, String> data2 = new HashMap<>();

        data2.put("country", "India");
        data2.put("creditCardNumber", "2111111111111111");
        data2.put("cvv", "550");
        data2.put("nameOnCard", "Tom2");
        data2.put("mail", "Tom22225@gmail.com");

        return new Object[][]{
                {data1},
                {data2}
        };
    }

    @DataProvider(name = "orderDataByMySql")
    public Object[][] orderDataByMySql() {
        return DatabaseUtils.getTestData(
                "SELECT CreditcardNo as creditCardNumber, CreditcardSecurityNo as cvv, MemEmail as mail FROM mem");
    }

    @DataProvider(name = "orderDataByExcel")
    public Object[][] orderDataByExcel() {
        return ExcelDataUtils.getTestData(
                FrameworkConstants.TESTDATA_DIR + "eshop/orderData.xlsx", "OrderData");
    }

    @DataProvider(name = "orderDataByJson")
    public Object[][] orderDataByJson() {
        return JsonDataUtils.getTestData(
                FrameworkConstants.TESTDATA_DIR + "eshop/orderData.json");
    }

    private OrderPage jump2OrderPage() {
        getDriver().get("https://rahulshettyacademy.com/client/#/auth/login");
        LoginPage loginPage = new LoginPage();
        ShoppingPage shoppingPage = loginPage.login("neux@gmail.com", "1q@W3e$R5t");
        shoppingPage.addCoatToCart();
        CartPage cartPage = shoppingPage.goToCart();
        return cartPage.checkout();

    }
}
