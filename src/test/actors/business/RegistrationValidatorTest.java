package actors.business;

import actors.messages.RegisterMessage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 22.09.16.
 */
public class RegistrationValidatorTest {
    @Test(dataProvider = "validRegisterMessages", dataProviderClass = TestDataProvider.class)
    public void testIsValid(RegisterMessage message) throws Exception {
        assertTrue(new RegistrationValidator().isValid(message));
    }

    @Test(dataProvider = "invalidRegisterMessages", dataProviderClass = TestDataProvider.class)
    public void testIsInvalid(RegisterMessage message) throws Exception {
        assertFalse(new RegistrationValidator().isValid(message));
    }


}