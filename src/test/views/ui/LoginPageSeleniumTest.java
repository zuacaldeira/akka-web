package views.ui;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 06.09.16.
 */
public class LoginPageSeleniumTest extends SeleniumTest {


    @Test
    public synchronized void login() {
        start();
        assertTrue(clickLogin());
        assertTrue(fillUsername("username@gmail.com"));
        assertTrue(fillPassword("Pas1sword"));
        assertTrue(clickLogin());
        assertTrue(super.inUserPage());
        stop();
    }

}
