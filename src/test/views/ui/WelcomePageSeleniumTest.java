package views.ui;

import actors.business.TestDataProvider;
import actors.messages.LoginMessage;
import actors.messages.RegisterMessage;
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


    @Test(dataProvider = "validRegisterMessages", dataProviderClass = TestDataProvider.class)
    public void testRegister(RegisterMessage message) throws Exception {
        start();
        clickRegister();
        fillUsername(message.getEmail());
        fillPassword(message.getPassword());
        fillPasswordConfirmation(message.getPassword());
        fillFullname(message.getFullname());
        clickRegister();
        stop();
    }


    @Test(dataProvider = "validLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testLogin(LoginMessage message) throws Exception {
        seed(message.getUsername(), message.getPassword());
        start();
        clickLogin();
        fillUsername(message.getUsername());
        fillPassword(message.getPassword());
        clickLogin();
        stop();
    }
}
