package practice.smoke.Login;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

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
    public void links() {
        System.out.println("practice.smoke.Login.loginPage.links");
    }

    @Test(groups = {"positive"})
    public void loginPositiveTypetest() {
        System.out.println("practice.smoke.Login.loginPage.loginPositiveTypetest");
        assert true;
    }


    @Parameters({"baseUrl", "username", "password"})
    @Test(groups = {"parametersTest"})
    public void loginPageTest(String baseUrl, String username, String password) {
        System.out.println("practice.smoke.Login.loginPage.loginPageTest");
        System.out.println(baseUrl + "/" + username + "/" + password);
    }


    @Test(dataProvider = "accountData",groups = {"parametersTest"})
    public void loginByDataProvider(String username, String password) {
        System.out.println("practice.smoke.Login.loginPage.loginByDataProvider");

        System.out.println(username + "/" + password);
    }


    @Test(groups = {"negative"})
    public void loginNegativeType() {
        System.out.println("practice.smoke.Login.loginPage.loginNegativeTypetest");
    }

    @Test(groups = {"negative"}, dependsOnMethods = {"loginPositiveTypetest"})
    public void invalidAccount() {
        System.out.println("practice.smoke.Login.loginPage.invalidAccount");

    }

    /**
     * dependsOnMethods：如果依賴的method failed，就不會執行
     */
    @Test(groups = {"negative"}, dependsOnMethods = {"loginPositiveTypetest"})
    public void invalidPassword() {
        System.out.println("practice.smoke.Login.loginPage.invalidPassword");

    }

    /**
     * enable= false:測試會跳過
     * timeOut=1000:測試執行超過 1 秒還沒結束，TestNG 會中斷它，並將其標記為失敗。
     */
    @Test(groups = "positive", enabled = false, timeOut = 1000)
    public void longByGoogle() {
        System.out.println("practice.smoke.Login.loginPage.longByGoogle");

    }


    @BeforeClass
    public void beforeClass() {
        System.out.println("practice.smoke.Login.loginPage.beforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("practice.smoke.Login.loginPage.afterClass");
    }


    @DataProvider(name = "accountData")
    public Object[][] getAccountAndPassWord() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{"user1", "pass1"});
        list.add(new Object[]{"user2", "pass2"});
        return list.toArray(new Object[list.size()][]);
    }
}
