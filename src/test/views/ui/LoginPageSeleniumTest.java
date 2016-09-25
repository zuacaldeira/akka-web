package views.ui;

import org.testng.annotations.Test;

/**
 * Created by zua on 06.09.16.
 */
public class LoginPageSeleniumTest extends SeleniumTest {


    @Test
    public synchronized void login() {
        start();
        clickLogin();
        fillUsername("username@gmail.com");
        fillPassword("Pas1sword");
        clickLogin();
        stop();
    }

}
