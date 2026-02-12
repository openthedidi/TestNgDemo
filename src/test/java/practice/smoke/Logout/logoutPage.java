package practice.smoke.Logout;

import org.testng.annotations.*;


public class logoutPage {
    @Test(groups = {"positive"})
    public void testLogoutPage(){
        System.out.println("test logoutPage");
    }

    @BeforeTest
    public void clearDbData(){
        System.out.println("practice.smoke.Logout.logoutPage.BeforeTest：clearDbData");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("practice.smoke.logoutPage.beforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("practice.smoke.logoutPage.afterSuite");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("practice.smoke.logoutPage.beforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("practice.smoke.logoutPage.afterClass");
    }

}
