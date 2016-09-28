package actors.messages.crud;

import graphs.entities.Entity;
import graphs.entities.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * Created by zua on 26.09.16.
 */
public class CrudMessageTest {
    @Test(dataProvider = "get")
    public <T extends Entity> void testGetValue(CrudMessage<T> message, Entity expected) throws Exception {
        assertEquals(message.getValue(), expected);
    }

    @Test(dataProvider = "set")
    public <T extends Entity> void testSetValue(CrudMessage<T> message, T old, T update) throws Exception {
        assertEquals(message.getValue(), old);

        message.setValue(update);
        assertNotEquals(message.getValue(), old, "InMessage value should not equal old value");
        assertEquals(message.getValue(), update);
    }




    @DataProvider(name = "get")
    public Object[][] getData() {
        User u = new User();
        return new Object[][] {
            {new CreateMessage<User>(u), u},
            {new ReadMessage<User>(10L), null},
            {new UpdateMessage<User>(u), u},
            {new DeleteMessage<User>(u), u},
        };
    }

    @DataProvider(name = "set")
    public Object[][] setData() {
        User u = new User();
        u.setId(10L);
        u.setEmail("Emaill");

        User u2 = new User();
        u2.setId(12L);

        return new Object[][] {
                {new CreateMessage<User>(u), u, u2},
                {new ReadMessage<User>(10L), null, u2},
                {new UpdateMessage<User>(u), u, u2},
                {new DeleteMessage<User>(u), u, u2},
        };
    }
}