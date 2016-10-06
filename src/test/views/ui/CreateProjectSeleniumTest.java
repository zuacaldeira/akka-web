package views.ui;

import actors.messages.ControlMessage;
import actors.mvc.views.StyleClassNames;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 04.10.16.
 */
public class CreateProjectSeleniumTest extends SeleniumTest {


    @Test
    public void createSimpleProject() {
        start();
        System.out.println("Browser opened");

        assertTrue(super.hasButtonNamed(ControlMessage.REGISTER.name()));
        assertTrue(clickRegister());

        assertTrue(fillFullname("Alexandre Zua Caldeira"));
        assertTrue(fillUsername("zuacaldeira@gmail.com"));
        assertTrue(fillPassword("PassWord1"));
        assertTrue(fillPasswordConfirmation("PassWord1"));
        assertTrue(clickRegister());


        assertTrue(super.hasElementNamed(StyleClassNames.EMAIL.getStyle()));
        assertTrue(fillUsername("zuacaldeira@gmail.com"));

        assertTrue(super.hasElementNamed(StyleClassNames.PASSWORD.getStyle()));
        assertTrue(fillPassword("PassWord1"));

        assertTrue(super.hasButtonNamed(ControlMessage.LOGIN.name()));
        assertTrue(clickLogin());


        assertTrue(super.hasButtonNamed(ControlMessage.PROJECT.name()));
        assertTrue(super.hasButtonNamed(ControlMessage.PROFILE.name()));

        assertTrue(clickProject());
        assertTrue(clickCreate());

        assertTrue(fillTitle("title"));
        assertTrue(fillDescription("description"));
        assertTrue(clickCreate());
        assertTrue(hasElementNamed(StyleClassNames.PROJECT_ACTOR.getStyle()));
        stop();
    }

}
