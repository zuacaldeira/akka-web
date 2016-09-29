package views.ui;

import actors.business.TestDataProvider;
import actors.messages.LoginMessage;
import actors.messages.RegisterMessage;
import actors.mvc.views.StyleClassNames;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 04.09.16.
 */
public class WelcomePageSeleniumTest extends SeleniumTest {

    @Test
    public void testWelcomePage() throws Exception {
        start();
        assertTrue(hasViewNamed(StyleClassNames.WELCOME_ACTOR.getStyle()));
        assertTrue(clickRegister());
        assertTrue(hasViewNamed(StyleClassNames.REGISTER_ACTOR.getStyle()));
        assertTrue(clickCancel());
        assertTrue(hasViewNamed(StyleClassNames.WELCOME_ACTOR.getStyle()));
        assertTrue(clickLogin());
        assertTrue(hasViewNamed(StyleClassNames.LOGIN_ACTOR.getStyle()));
        assertTrue(clickCancel());
        assertTrue(hasViewNamed(StyleClassNames.WELCOME_ACTOR.getStyle()));
        stop();
    }


    @Test(dataProvider = "validRegisterMessages", dataProviderClass = TestDataProvider.class)
    public void testRegister(RegisterMessage message) throws Exception {
        start();
        assertTrue(hasViewNamed(StyleClassNames.WELCOME_ACTOR.getStyle()));
        assertTrue(clickRegister());
        assertTrue(hasViewNamed(StyleClassNames.REGISTER_ACTOR.getStyle()));
        assertTrue(fillUsername(message.getEmail()));
        assertTrue(fillPassword(message.getPassword()));
        assertTrue(fillPasswordConfirmation(message.getPassword()));
        assertTrue(fillFullname(message.getFullname()));
        assertTrue(clickRegister());
        assertTrue(hasViewNamed(StyleClassNames.LOGIN_ACTOR.getStyle()));
        stop();
    }


    @Test(dataProvider = "validLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testLogin(LoginMessage message) throws Exception {
        seed(message.getUsername(), message.getPassword());
        start();
        assertTrue(hasViewNamed(StyleClassNames.WELCOME_ACTOR.getStyle()));
        assertTrue(clickLogin());
        assertTrue(hasViewNamed(StyleClassNames.LOGIN_ACTOR.getStyle()));
        assertTrue(fillUsername(message.getUsername()));
        assertTrue(fillPassword(message.getPassword()));
        assertTrue(clickLogin());
        assertTrue(inUserPage());
        stop();
    }
}
