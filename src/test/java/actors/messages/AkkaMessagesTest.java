package actors.messages;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 31.08.16.
 */
public class AkkaMessagesTest {

    @Test
    public void testWelcomeActorMessages() {
        assertTrue(AkkaMessages.getWelcomeActorMessages().contains(AkkaMessages.REGISTER));
        assertTrue(AkkaMessages.getWelcomeActorMessages().contains(AkkaMessages.LOGIN));
    }

    @Test
    public void testRegisterActorMessages() {
        assertTrue(AkkaMessages.getRegisterActorMessages().contains(AkkaMessages.REGISTER));
        assertFalse(AkkaMessages.getRegisterActorMessages().contains(AkkaMessages.LOGIN));
    }

    @Test
    public void testLoginActorMessages() {
        assertFalse(AkkaMessages.getLoginActorMessages().contains(AkkaMessages.REGISTER));
        assertTrue(AkkaMessages.getLoginActorMessages().contains(AkkaMessages.LOGIN));
    }

}