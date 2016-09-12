package views.ui;

import org.testng.annotations.Test;

/**
 * Created by zua on 04.09.16.
 */
public class WelcomePageSeleniumTest extends SeleniumTest {

    @Test
    public void testWelcomePage() throws Exception {
        start();
        clickRegister();
        clickRegister();
        clickCancel();
        clickLogin();
        clickLogin();
        clickCancel();
        stop();
    }


    @Test
    public void testRegister() throws Exception {
        start();
        clickRegister();
        clickRegister();
        fillUsername("username");
        fillPassword("password");
        fillPasswordConfirmation("password");
        fillFullname("fullname");
        clickRegister();
        clickRegister();
        stop();
    }


    @Test
    public void testLogin() throws Exception {
        testRegister();
        start();
        clickLogin();
        clickLogin();
        fillUsername("username");
        fillPassword("password");
        clickLogin();
        clickLogin();
        stop();
    }
}
