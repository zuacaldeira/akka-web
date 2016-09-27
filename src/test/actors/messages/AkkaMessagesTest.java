package actors.messages;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 31.08.16.
 */
public class AkkaMessagesTest {

    @Test
    public void testMessageDefined() {
        assertNotNull(ControlMessage.REGISTER);
        assertNotNull(ControlMessage.LOGIN);
        assertNotNull(ControlMessage.FAILED);
        assertNotNull(ControlMessage.INVALID);
    }

}