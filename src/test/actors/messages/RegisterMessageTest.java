package actors.messages;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created by zua on 02.09.16.
 */
public class RegisterMessageTest {

    @Test
    public void testToString() throws Exception {
        RegisterMessage message = new RegisterMessage("username", "password", "fullname");
        assertNotNull(message.toString());
        assertFalse(message.toString().isEmpty());
    }

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