package graphs.entities;

import graphs.entities.nodes.Account;
import graphs.entities.nodes.LoginAs;
import graphs.entities.nodes.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 01.09.16.
 */
public class LoginAsTest {

    @Test(dataProvider = "simple")
    public void testGetUser(LoginAs loginAs, User u, Account a) throws Exception {
        assertEquals(loginAs.getUser(), u);
        assertEquals(loginAs.getAccount(), a);
    }

    @Test(dataProvider = "simple")
    public void testGetAccount(LoginAs loginAs, User u, Account a) throws Exception {
        assertEquals(loginAs.getUser(), u);
        assertEquals(loginAs.getAccount(), a);
    }

    @Test(dataProvider = "simple")
    public void testSetUser(LoginAs loginAs, User u, Account a) throws Exception {
        User u1 = new User();
        loginAs.setUser(u1);
        assertEquals(u1, loginAs.getUser());
        assertEquals(loginAs.getAccount(), a);
    }

    @Test(dataProvider = "simple")
    public void testSetAccount(LoginAs loginAs, User u, Account a) throws Exception {
        Account a1 = new Account();
        loginAs.setAccount(a1);
        assertEquals(u, loginAs.getUser());
        assertEquals(a1, loginAs.getAccount());
    }


    @Test(dataProvider = "equals")
    public void testEquals(LoginAs a, LoginAs b) throws Exception {
        assertTrue(a.equals(b));
    }

    @Test(dataProvider = "equals")
    public void testHashCode(LoginAs a, LoginAs b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test(dataProvider = "inequals")
    public void testInequals(LoginAs a, Object b) throws Exception {
        assertFalse(a.equals(b));
    }

    @DataProvider(name = "equals")
    public Object[][] equals() {
        return new Object[][] {
            {new LoginAs(new User(), new Account()), new LoginAs(new User(), new Account())},
            {new LoginAs(new User("e", "f"), new Account("u", "p")), new LoginAs(new User("e", "f"), new Account("u", "p"))}
        };
    }

    @DataProvider(name = "inequals")
    public Object[][] inequals() {
        LoginAs loginAs1 = new LoginAs(new User(), new Account());
        LoginAs loginAs2 = new LoginAs(new User("e", "f"), new Account("u", "p"));
        return new Object[][] {
                {loginAs1, loginAs2},
                {loginAs2, loginAs1},
                {loginAs1, new User()}
        };
    }

    @DataProvider(name = "simple")
    public Object[][] simple() {
        User u = new User();
        User v = new User("e", "f");

        Account a = new Account();
        Account b = new Account("u", "p");
        return new Object[][] {
                {new LoginAs(u, a), u, a},
                {new LoginAs(v, b), v, b}
        };
    }


}