package actors.mvc;

import actors.exceptions.UserUniquenessViolation;
import org.testng.annotations.Test;

/**
 * Created by zua on 05.10.16.
 */
public class UserUniquenessViolationTest {

    @Test(expectedExceptions = UserUniquenessViolation.class)
    public void testException() {
        throw new UserUniquenessViolation("azcazcazc");
    }

}