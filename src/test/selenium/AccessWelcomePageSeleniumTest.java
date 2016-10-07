package selenium;

import actors.messages.ControlMessage;
import actors.mvc.views.StyleClassNames;
import org.testng.annotations.Test;
import views.ui.SeleniumTest;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 04.10.16.
 */
public class AccessWelcomePageSeleniumTest extends SeleniumTest {

    @Test
    public void testAccessWelcomeActorView() {
        start();
        assertTrue(super.hasElementNamed(StyleClassNames.WELCOME_ACTOR.getStyle()));
        assertNotNull(super.findWelcomeActorView());
        // Lookup for REGISTER Button
        assertTrue(super.hasButtonNamed(ControlMessage.REGISTER.name()));
        assertNotNull(super.findButton(ControlMessage.REGISTER.name()));
        // Lookup for LOGIN Button
        assertTrue(super.hasButtonNamed(ControlMessage.LOGIN.name()));
        assertNotNull(super.findButton(ControlMessage.LOGIN.name()));
        stop();
    }

    @Test
    public void testAccessRegisterActorView() {
        start();
        assertTrue(super.hasElementNamed(StyleClassNames.WELCOME_ACTOR.getStyle()));
        assertNotNull(super.findWelcomeActorView());
        // Lookup for REGISTER Button
        assertTrue(super.hasButtonNamed(ControlMessage.REGISTER.name()));
        assertNotNull(super.findButton(ControlMessage.REGISTER.name()));
        assertTrue(clickRegister());
        assertTrue(super.hasElementNamed(StyleClassNames.REGISTER_ACTOR.getStyle()));
        stop();
    }

    @Test
    public void testAccessLoginActorView() {
        start();
        assertTrue(super.hasElementNamed(StyleClassNames.WELCOME_ACTOR.getStyle()));
        assertNotNull(super.findWelcomeActorView());
        // Lookup for REGISTER Button
        assertTrue(super.hasButtonNamed(ControlMessage.LOGIN.name()));
        assertNotNull(super.findButton(ControlMessage.LOGIN.name()));
        assertTrue(clickRegister());
        assertTrue(super.hasElementNamed(StyleClassNames.LOGIN_ACTOR.getStyle()));
        stop();
    }

}
