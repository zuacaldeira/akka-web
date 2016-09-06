package views.ui;

import actors.core.exceptions.IllegalRegistrationException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by zua on 05.09.16.
 */
public class RegisterPageSeleniumTest extends SeleniumTest {

    @Test(dataProvider = "registerData")
    public void testRegister(String username, String password, String fullname) throws Exception {
        start();
        clickRegister();
        fillUsername(username);
        fillPassword(password);
        fillPasswordConfirmation(password);
        fillFullname(fullname);
        clickRegister();
        stop();
    }

    @Test(dataProvider = "invalidRegisterData", expectedExceptions = IllegalRegistrationException.class)
    public void testInvalidRegister(String username, String password, String fullname) throws Exception {
        start();
        clickRegister();
        fillUsername(username);
        fillPassword(password);
        fillPasswordConfirmation(password);
        fillFullname(fullname);
        clickRegister();
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
