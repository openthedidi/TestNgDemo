package skbank.smoke.Loan;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class houseLoanPage {
    @Test
    public void testHouseLoan(){
        System.out.println("test house loan");
    }

    @BeforeTest
    public void clearDbData(){
        System.out.println("skbank.smoke.Logout.houseLoanPage.BeforeTest：clearDbData");
    }

}
