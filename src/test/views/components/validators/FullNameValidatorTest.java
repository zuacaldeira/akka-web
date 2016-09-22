package views.components.validators;

import actors.business.TestDataProvider;
import actors.messages.RegisterMessage;
import com.vaadin.data.Validator;
import org.testng.annotations.Test;

/**
 * Created by zua on 22.09.16.
 */
public class FullNameValidatorTest {


    @Test(dataProvider = "validRegisterMessages",
            dataProviderClass = TestDataProvider.class)
    public void testValidFullname(RegisterMessage message) {
        new FullNameValidator("").validate(message.getFullname());
    }

    @Test(expectedExceptions = Validator.InvalidValueException.class)
    public void testNullFullname() {
        new FullNameValidator("").validate(null);
    }

}