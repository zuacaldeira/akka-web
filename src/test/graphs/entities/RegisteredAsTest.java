package graphs.entities;

import graphs.entities.nodes.Account;
import graphs.entities.nodes.RegisteredAs;
import graphs.entities.nodes.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 01.09.16.
 */
public class RegisteredAsTest {
    @Test(dataProvider = "equals")
    public void testEquals(RegisteredAs a, RegisteredAs b) throws Exception {
        assertTrue(a.equals(b));
    }

    @Test(dataProvider = "equals")
    public void testHashCode(RegisteredAs a, RegisteredAs b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test(dataProvider = "inequals")
    public void testInequals(Object a, Object b) throws Exception {
        assertFalse(a.equals(b));
    }

    @DataProvider(name = "equals")
    public Object[][] equals() {
        return new Object[][] {
                {new RegisteredAs(new User(), new Account()), new RegisteredAs(new User(), new Account())},
                {new RegisteredAs(new User("e", "f"), new Account("u", "p")), new RegisteredAs(new User("e", "f"), new Account("u", "p"))}
        };
    }

    @DataProvider(name = "inequals")
    public Object[][] inequals() {
        RegisteredAs r1 = new RegisteredAs(new User(), new Account());
        RegisteredAs r2 = new RegisteredAs(new User("e", "f"), new Account("u", "p"));
        return new Object[][] {
                {r1, r2}, {r2, r1}, {r1, new User()}
        };
    }
}