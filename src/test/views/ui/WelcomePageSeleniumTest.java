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
        clickCancel();
        clickLogin();
        clickCancel();
        stop();
    }


    @Test
    public void testRegister() throws Exception {
        start();
        clickRegister();
        fillUsername("username");
        fillPassword("password");
        fillPasswordConfirmation("password");
        fillFullname("fullname");
        clickRegister();
        stop();
    }


    @Test
    public void testLogin() throws Exception {
        testRegister();
        start();
        clickLogin();
        fillUsername("username");
        fillPassword("password");
        clickLogin();
        stop();
    }
}
