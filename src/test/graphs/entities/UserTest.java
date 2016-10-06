package graphs.entities;

import graphs.entities.nodes.Account;
import graphs.entities.nodes.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by zua on 01.09.16.
 */
public class UserTest {
    @Test(dataProvider = "equals")
    public void testEquals(User a, User b) throws Exception {
        assertTrue(a.equals(b));
    }

    @Test(dataProvider = "equals")
    public void testHashCode(User a, User b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @DataProvider(name = "equals")
    public Object[][] equals() {
        return new Object[][] {
                {new User(), new User()},
                {new User("u", "p"), new User("u", "p")}
        };
    }

    @Test(dataProvider = "inequals")
    public void testInequals(Object a, Object b) throws Exception {
        assertFalse(a.equals(b));
    }

    @Test(dataProvider = "inequals")
    public void testHashCode(Object a, Object b) throws Exception {
        if (a != null && b != null) {
            assertNotEquals(a.hashCode(), b.hashCode());
        }
    }

    @DataProvider(name = "inequals")
    public Object[][] inequals() {
        return new Object[][] {
                {new User(), new User("u", "p")},
                {new User("u", "p"), new User()},
                {new Account("u", "p"), new User("u", "f")},
        };
    }
}