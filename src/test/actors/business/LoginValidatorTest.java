package actors.business;

import actors.messages.LoginMessage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 20.09.16.
 */
public class LoginValidatorTest {

    @Test(dataProvider = "validUsernames")
    public void testIsValidUsername(String username) throws Exception {
        assertTrue(new LoginValidator().isValidUsername(username));
    }

    @Test(dataProvider = "invalidUsernames")
    public void testIsInvalidUsername(String username) throws Exception {
        assertFalse(new LoginValidator().isValidUsername(username));
    }


    @Test(dataProvider = "validPasswords")
    public void testHasUpperCase(String password) throws Exception {
        assertTrue(new LoginValidator().hasUpperCase(password));
    }

    @Test(dataProvider = "validPasswords")
    public void testHasLowerCase(String password) throws Exception {
        assertTrue(new LoginValidator().hasLowerCase(password));
    }

    @Test(dataProvider = "validPasswords")
    public void testHasDigit(String password) throws Exception {
        assertTrue(new LoginValidator().hasDigit(password));
    }

    @Test(dataProvider = "validPasswords")
    public void testIsValidPassword(String password) throws Exception {
        assertTrue(new LoginValidator().isValidPassword(password));
    }

    @Test(dataProvider = "validPasswords")
    public void testIsStrong(String password) throws Exception {
        assertTrue(new LoginValidator().isStrong(password));
    }

    @Test(dataProvider = "invalidPasswords")
    public void testIsInvalidPassword(String password) throws Exception {
        assertFalse(new LoginValidator().isValidPassword(password));
    }

    @Test(dataProvider = "validLoginMessages")
    public void testIsValid(LoginMessage message) throws Exception {
        assertTrue(new LoginValidator().isValid(message));
    }


    @DataProvider(name = "validUsernames")
    public Object[][] validUsernames() {
        return new Object[][] {
                {"asdf@asdf.com"},
                {"Asdf@asdf.com"},
                {"asdf1@asdf.com"},
                {"asd@asdf.com"}
        };
    }

    @DataProvider(name = "invalidUsernames")
    public Object[][] invalidUsernames() {
        return new Object[][] {
                {"asdf@asdf-com"},
                {"asdf@asdfcom"},
                {"1asdf@asdf.com"},
                {"asd.asdf.com"}
        };
    }

    @DataProvider(name = "validPasswords")
    public Object[][] validPasswords() {
        return new Object[][] {
                {"Aa1aaa"},
                {"aA1aaa"},
                {"1Aaaaa"}
        };
    }

    @DataProvider(name = "invalidPasswords")
    public Object[][] invalidPasswords() {
        return new Object[][] {
                {"Aa1"},
                {"aa1aaa"},
                {"AA1AAA"},
                {"aAaaaa"}
        };
    }


    @DataProvider(name = "validLoginMessages")
    public Object[][] validLoginMessages() {
        return new Object[][] {
                {new LoginMessage("username@username.com", "Password1")},
                {new LoginMessage("username2@gmail.com", "PaSsw1ord")}
        };
    }
}