package actors.messages;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 31.08.16.
 */
public class AkkaMessagesTest {

    @Test
    public void testMessageDefined() {
        assertNotNull(AkkaMessage.REGISTER);
        assertNotNull(AkkaMessage.REGISTER_FAILED);
        assertNotNull(AkkaMessage.REGISTER_INVALID);
        assertNotNull(AkkaMessage.LOGIN);
        assertNotNull(AkkaMessage.FAILED);
        assertNotNull(AkkaMessage.INVALID);
    }

}