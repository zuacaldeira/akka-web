package graphs.entities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 01.09.16.
 */
public class LoginTest {
    @Test(dataProvider = "equals")
    public void testEquals(Login a, Login b) throws Exception {
        assertTrue(a.equals(b));
    }



    @Test(dataProvider = "equals")
    public void testHashCode(Login a, Login b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @DataProvider(name = "equals")
    public Object[][] equals() {
        return new Object[][] {
            {new Login(new User(), new Account()), new Login(new User(), new Account())},
            {new Login(new User("e", "f"), new Account("u", "p")), new Login(new User("e", "f"), new Account("u", "p"))}
        };
    }

}