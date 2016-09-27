package actors.messages.crud;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * Created by zua on 26.09.16.
 */
public class CrudMessageTest {
    @Test(dataProvider = "get")
    public <T> void testGetValue(CrudMessage<T> message, T expected) throws Exception {
        assertEquals(message.getValue(), expected);
    }

    @Test(dataProvider = "set")
    public <T> void testSetValue(CrudMessage<T> message, T expected, T update) throws Exception {
        assertEquals(message.getValue(), expected);

        message.setValue(update);
        assertNotEquals(message.getValue(), expected);
        assertEquals(message.getValue(), update);
    }




    @DataProvider(name = "get")
    public Object[][] getData() {
        return new Object[][] {
            {new CreateMessage<Integer>(10), 10},
                {new CreateMessage<Integer>(10), 10},
                {new ReadMessage<Integer>(10L), 10L},
                {new UpdateMessage<Integer>(10), 10},
                {new DeleteMessage<Integer>(10), 10},
        };
    }

    @DataProvider(name = "set")
    public Object[][] setData() {
        return new Object[][] {
                {new CreateMessage<Integer>(10), 10, 12},
                {new ReadMessage<Integer>(10L), 10L, 12L},
                {new UpdateMessage<Integer>(10), 10, 12},
                {new DeleteMessage<Integer>(10), 10, 12},
        };
    }
}