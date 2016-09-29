package views.ui;

import actors.mvc.views.StyleClassNames;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 05.09.16.
 */
public class RegisterPageSeleniumTest extends SeleniumTest {

    @Test(dataProvider = "registerData")
    public void testRegister(String username, String password, String fullname) throws Exception {
        start();
        assertTrue(clickRegister());
        assertTrue(fillUsername(username));
        assertTrue(fillPassword(password));
        assertTrue(fillPasswordConfirmation(password));
        assertTrue(fillFullname(fullname));
        assertTrue(clickRegister());
        stop();
    }

    @Test(dataProvider = "invalidRegisterData")
    public void testInvalidRegister(String username, String password, String fullname) throws Exception {
        start();
        assertTrue(clickRegister());
        assertTrue(fillUsername(username));
        assertTrue(fillPassword(password));
        assertTrue(fillPasswordConfirmation(password));
        assertTrue(fillFullname(fullname));
        assertTrue(clickRegister());
        assertTrue(super.hasViewNamed(StyleClassNames.REGISTER_ACTOR.getStyle()));
        stop();
    }



    @DataProvider(name = "registerData")
    public Object[][] provideRegisterData() {
        return new Object[][] {
                {"zuacaldeira@gmail.com", "password", "fullname"},
                {"elisa.sa@gmail.com", "password", "fullname"}
        };
    }

    @DataProvider(name = "invalidRegisterData")
    public Object[][] provideInvalidRegisterData() {
        return new Object[][] {
                {"zuacaldeira", "password", "fullname"},
                {"elisa.sa@gmail.com", "pass", "fullname"},
                {"elisa.sa@gmail.com", "password", ""}/*,
                {null, "password", "fullname"},
                {"elisa.sa@gmail.com", null, "fullname"},
                {"elisa.sa@gmail.com", "password", null},
                {null, null, null},*/
        };
    }


}
