package actors.messages;

import actors.messages.LoginMessage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by zua on 02.09.16.
 */
public class LoginMessageTest {
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