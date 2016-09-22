package actors.business;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 22.09.16.
 */
public class UsernameValidatorTest {

    @Test(dataProvider = "usernames")
    public void testIsValid(String username) throws Exception {
        assertTrue(new UsernameValidator().isValid(username));
    }

    @DataProvider(name = "usernames")
    public Object[][] usernames() {
        return new Object[][] {
                {"asdf@asdf.asd"},
                {"a1s1d1@asdf.asd"}
        };
    }

}