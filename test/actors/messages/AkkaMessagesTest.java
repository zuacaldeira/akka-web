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
        assertTrue(AkkaMessages.WELCOME_ACTOR_MESSAGES.contains(AkkaMessages.REGISTER));
        assertTrue(AkkaMessages.WELCOME_ACTOR_MESSAGES.contains(AkkaMessages.LOGIN));
    }

    @Test
    public void testRegisterActorMessages() {
        assertTrue(AkkaMessages.REGISTER_ACTOR_MESSAGES.contains(AkkaMessages.REGISTER));
        assertFalse(AkkaMessages.REGISTER_ACTOR_MESSAGES.contains(AkkaMessages.LOGIN));
    }

    @Test
    public void testLoginActorMessages() {
        assertFalse(AkkaMessages.LOGIN_ACTOR_MESSAGES.contains(AkkaMessages.REGISTER));
        assertTrue(AkkaMessages.LOGIN_ACTOR_MESSAGES.contains(AkkaMessages.LOGIN));
    }

}