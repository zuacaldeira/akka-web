package actors;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.log4testng.Logger;

/**
 * Created by zua on 27.09.16.
 */
public abstract class AbstractTest {
    private Logger log;

    @BeforeMethod
    public void atTestBegin2() {
        System.out.println("[START] -----------------------------------------");
        System.out.println(this.getClass().getCanonicalName());
    }
    @AfterMethod
    public void atTestEnd() {
        System.out.println("[END  ] -----------------------------------------\n");
    }
}
