package actors.exceptions;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by zua on 22.09.16.
 */
public class ExceptionsTest {

    @Test(dataProvider = "exceptions", expectedExceptions = IllegalArgumentException.class)
    public void testInvalidLoginException(IllegalArgumentException ile) {
        throw ile;
    }

    @DataProvider(name = "exceptions")
    public Object[][] exceptions() {
        return new Object[][] {
                {new InvalidLoginException("Invalid LoginAs")},
                {new MultipleRegistrationException("Multiple registration")},
                {new InvalidRegistrationException("Invalid registration")},
                {new UnregisteredUserException("Me")},
                {new UnregisteredUserException(null)},
                {new UnexpectedException("Invalid registration", new InvalidRegistrationException("Invalid registration"))},
        };
    }

}