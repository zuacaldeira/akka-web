package graphs.entities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 01.09.16.
 */
public class LoginTest {

    @Test(dataProvider = "simple")
    public void testGetUser(Login login, User u, Account a) throws Exception {
        assertEquals(login.getUser(), u);
        assertEquals(login.getAccount(), a);
    }

    @Test(dataProvider = "simple")
    public void testGetAccount(Login login, User u, Account a) throws Exception {
        assertEquals(login.getUser(), u);
        assertEquals(login.getAccount(), a);
    }

    @Test(dataProvider = "simple")
    public void testSetUser(Login login, User u, Account a) throws Exception {
        User u1 = new User();
        login.setUser(u1);
        assertEquals(u1, login.getUser());
        assertEquals(login.getAccount(), a);
    }

    @Test(dataProvider = "simple")
    public void testSetAccount(Login login, User u, Account a) throws Exception {
        Account a1 = new Account();
        login.setAccount(a1);
        assertEquals(u, login.getUser());
        assertEquals(a1, login.getAccount());
    }


    @Test(dataProvider = "equals")
    public void testEquals(Login a, Login b) throws Exception {
        assertTrue(a.equals(b));
    }

    @Test(dataProvider = "equals")
    public void testHashCode(Login a, Login b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test(dataProvider = "inequals")
    public void testInequals(Login a, Object b) throws Exception {
        assertFalse(a.equals(b));
    }

    @DataProvider(name = "equals")
    public Object[][] equals() {
        return new Object[][] {
            {new Login(new User(), new Account()), new Login(new User(), new Account())},
            {new Login(new User("e", "f"), new Account("u", "p")), new Login(new User("e", "f"), new Account("u", "p"))}
        };
    }

    @DataProvider(name = "inequals")
    public Object[][] inequals() {
        Login login1 = new Login(new User(), new Account());
        Login login2 = new Login(new User("e", "f"), new Account("u", "p"));
        return new Object[][] {
                {login1, login2},
                {login2, login1},
                {login1, new User()}
        };
    }

    @DataProvider(name = "simple")
    public Object[][] simple() {
        User u = new User();
        User v = new User("e", "f");

        Account a = new Account();
        Account b = new Account("u", "p");
        return new Object[][] {
                {new Login(u, a), u, a},
                {new Login(v, b), v, b}
        };
    }


}