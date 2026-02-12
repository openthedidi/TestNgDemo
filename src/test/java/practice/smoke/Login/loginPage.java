package practice.smoke.Login;

import org.testng.annotations.*;

public class loginPage {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("practice.smoke.Login.loginPage.beforeSuite");
    }

    @BeforeTest
    public void clearDbData() {
        System.out.println("practice.smoke.Login.loginPage.BeforeTest：clearDbData");
    }

    @Test(groups = {"positive"})
    public void links(){
        System.out.println("practice.smoke.Login.loginPage.links");
    }

    @Test(groups = {"positive"})
    public void loginPositiveTypetest() {
        System.out.println("practice.smoke.Login.loginPage.loginPositiveTypetest");
    }


    @Test(groups = {"negative"})
    public void loginNegativeType() {
        System.out.println("practice.smoke.Login.loginPage.loginNegativeTypetest");
    }

    @Test(groups = {"negative"})
    public void invalidAccount() {
        System.out.println("practice.smoke.Login.loginPage.invalidAccount");

    }


    @Test(groups = {"negative"})
    public void invalidPassword() {
        System.out.println("practice.smoke.Login.loginPage.invalidPassword");

    }


    @BeforeClass
    public void beforeClass() {
        System.out.println("practice.smoke.Login.loginPage.beforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("practice.smoke.Login.loginPage.afterClass");
    }


}
