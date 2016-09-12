package actors.messages;

import actors.messages.RegisterMessage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by zua on 02.09.16.
 */
public class RegisterMessageTest {
    @Test
    public void testGetEmail() throws Exception {
        RegisterMessage message = new RegisterMessage("username", "password", "fullname");
        assertEquals("username", message.getEmail());
    }

    @Test
    public void testGetPassword() throws Exception {
        RegisterMessage message = new RegisterMessage("username", "password", "fullname");
        assertEquals("password", message.getPassword());
    }

    @Test
    public void testGetFullname() throws Exception {
        RegisterMessage message = new RegisterMessage("username", "password", "fullname");
        assertEquals("fullname", message.getFullname());
    }

}