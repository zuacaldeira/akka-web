package views.ui;

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
        seed("zuacaldeira@gmail.com", "PassWord1");
        assertTrue(clickLogin());
        assertTrue(fillUsername("zuacaldeira@gmail.com"));
        assertTrue(fillPassword("PassWord1"));
        assertTrue(clickLogin());
        createProject("Project", "A Simple Project.");
        stop();
    }

    private void createProject(String title, String description) {
        assertTrue(clickProject());
        assertTrue(clickCreate());
        assertTrue(fillTitle(title));
        assertTrue(fillDescription(description));
        assertTrue(clickCreate());
        assertTrue(hasViewNamed(StyleClassNames.PROJECT_ACTOR.getStyle()));
    }




    private void register() {
        assertTrue(clickRegister());
        assertTrue(fillFullname("Alexandre Zua Caldeira"));
        assertTrue(fillUsername("zuacaldeira@gmail.com"));
        assertTrue(fillPassword("PassWord1"));
        assertTrue(fillPasswordConfirmation("PassWord1"));
        assertTrue(clickRegister());
    }
}
