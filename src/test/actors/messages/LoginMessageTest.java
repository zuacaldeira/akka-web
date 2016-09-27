package actors.messages;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created by zua on 02.09.16.
 */
public class LoginMessageTest {
    @Test
    public void testToString() throws Exception {
        LoginMessage message = new LoginMessage("username", "password");
        assertNotNull(message.toString().isEmpty());
        assertFalse(message.toString().isEmpty());
    }

    @Test
    public void testGetUsername() throws Exception {
        LoginMessage message = new LoginMessage("username", "password");
        assertEquals("username", message.getUsername());
    }

    @Test
    public void testGetPassword() throws Exception {
        LoginMessage message = new LoginMessage("username", "password");
        assertEquals("password", message.getPassword());
    }

}