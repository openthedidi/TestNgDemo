package skbank.smoke.Creditcard;

import org.apache.logging.log4j.core.util.JsonUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class homePage {

    @BeforeTest
    public void clearDbData(){
        System.out.println("skbank.smoke.Creditcard.homePage.BeforeTest：clearDbData");
    }


    @Test
    public void cardPageFilter(){
        System.out.println("test home page");
    }

    @Test
    public void cardPageBtn(){
        System.out.println("test cardPageBtn");
    }

    @Test
    public void cardDetail(){
        System.out.println("test cardDetail");
    }

    @Test
    public void cardTags(){
        System.out.println("test cardTags");
    }

    @Test
    public void links(){
        System.out.println("test links");
    }

}
