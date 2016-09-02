package graphs.entities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
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

    @Test(dataProvider = "inequals")
    public void testInequals(Object a, Object b) throws Exception {
        assertFalse(a.equals(b));
    }

    @DataProvider(name = "equals")
    public Object[][] equals() {
        return new Object[][] {
                {new Registration(new User(), new Account()), new Registration(new User(), new Account())},
                {new Registration(new User("e", "f"), new Account("u", "p")), new Registration(new User("e", "f"), new Account("u", "p"))}
        };
    }

    @DataProvider(name = "inequals")
    public Object[][] inequals() {
        Registration r1 = new Registration(new User(), new Account());
        Registration r2 = new Registration(new User("e", "f"), new Account("u", "p"));
        return new Object[][] {
                {r1, r2}, {r2, r1}, {r1, new User()}
        };
    }
}