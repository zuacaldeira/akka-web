package views.components.validators;

import actors.business.InvalidPasswordException;
import actors.business.TestDataProvider;
import actors.messages.RegisterMessage;
import com.vaadin.data.Validator;
import com.vaadin.ui.PasswordField;
import org.testng.annotations.Test;

/**
 * Created by zua on 22.09.16.
 */
public class PasswordValidatorTest {


    @Test(dataProvider = "validRegisterMessages",
            dataProviderClass = TestDataProvider.class)
    public void testValidPassword(RegisterMessage message) {
        new PasswordValidator("Invalid Password").validate(message.getPassword());
    }

    @Test(dataProvider = "validRegisterMessages",
            dataProviderClass = TestDataProvider.class)
    public void testValidPasswordConfirmation(RegisterMessage message) {
        new PasswordConfirmationValidator("Invalid Password", new PasswordField("Password: ", message.getPassword())).validate(message.getPassword());
    }

    @Test(dataProvider = "invalidPasswords",
            dataProviderClass = TestDataProvider.class,
            expectedExceptions = InvalidPasswordException.class)
    public void testInvalidPassword(String password) {
        new PasswordValidator("Invalid Password").validate(password);
    }

    @Test(dataProvider = "invalidPasswords",
            dataProviderClass = TestDataProvider.class,
            expectedExceptions = InvalidPasswordException.class)
    public void testInvalidPasswordConfirmation(String password) {
        new PasswordConfirmationValidator("Invalid Password", new PasswordField("Password: ", password)).validate(password);
    }

    @Test(dataProvider = "validPasswords",
            dataProviderClass = TestDataProvider.class,
            expectedExceptions = Validator.InvalidValueException.class)
    public void testDistinctPasswordConfirmation(String password) {
        new PasswordConfirmationValidator("Invalid Password", new PasswordField("Password: ", password)).validate(password+1);
    }
}