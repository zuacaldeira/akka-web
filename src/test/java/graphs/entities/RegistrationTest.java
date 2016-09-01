package graphs.entities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 01.09.16.
 */
public class RegistrationTest {
    @Test(dataProvider = "equals")
    public void testEquals(Registration a, Registration b) throws Exception {
        assertTrue(a.equals(b));
    }

    @Test(dataProvider = "equals")
    public void testHashCode(Registration a, Registration b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @DataProvider(name = "equals")
    public Object[][] equals() {
        return new Object[][] {
                {new Registration(new User(), new Account()), new Registration(new User(), new Account())},
                {new Registration(new User("e", "f"), new Account("u", "p")), new Registration(new User("e", "f"), new Account("u", "p"))}
        };
    }
}