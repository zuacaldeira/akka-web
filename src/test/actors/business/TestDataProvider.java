package actors.business;

import actors.messages.LoginMessage;
import actors.messages.RegisterMessage;
import org.testng.annotations.DataProvider;

import static actors.messages.ControlMessage.LOGIN;
import static actors.messages.ControlMessage.REGISTER;

/**
 * Created by zua on 22.09.16.
 */
public class TestDataProvider {
    @DataProvider(name = "controlMessages")
    public static Object[][] getControlMessages() {
        return new Object[][] {
                {REGISTER},
                {LOGIN}
        };
    }

    @DataProvider(name = "validRegisterMessages")
    public static Object[][] getRegisterMessages() {
        return new Object[][] {
                {new RegisterMessage("username1@gmail.com", "pAss1word", "fullname")},
        };
    }

    @DataProvider(name = "invalidRegisterMessages")
    public static Object[][] getInvalidRegisterMessages() {
        return new Object[][] {
                {new RegisterMessage("1username@gmail.com", "pAss1word", "fullname")},
                {new RegisterMessage("usernameATgmail.com", "pAss1word", "fullname")},
                {new RegisterMessage("username@gmail.com", "pass1word", "fullname")}
        };
    }

    @DataProvider(name = "validPasswords")
    public static Object[][] getValidPasswords() {
        return new Object[][] {
                {"Pass1word"}
        };
    }

    @DataProvider(name = "invalidPasswords")
    public static Object[][] getInvalidPasswords() {
        return new Object[][] {
                {"pass1word"},
                {"pAssword"},
                {"PASS1WORD"},
                {null}
        };
    }

    @DataProvider(name = "validLoginMessages")
    public static Object[][] validLoginMessages() {
        return new Object[][] {
                {new LoginMessage("username2@gmail.com", "PaSsw1ord")}
        };
    }

    @DataProvider(name = "invalidLoginMessages")
    public static Object[][] getInvalidLoginMessages() {
        return new Object[][] {
                {new LoginMessage("username.gmail.com", "PaSsw1ord")},
                {new LoginMessage("username3@gmail.com", "passw1ord")}
        };
    }

    @DataProvider(name = "failedLoginMessages")
    public static Object[][] getFailedLoginMessages() {
        return new Object[][] {
        };
    }

}
