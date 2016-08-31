package views.actors;

import actors.messages.AkkaMessages;
import org.testng.annotations.Test;
import views.factories.ActorsViewFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 31.08.16.
 */
public class WelcomeActorViewTest {

    @Test
    public void testWelcomeActorView() {
        ActorView actorView = ActorsViewFactory.getWelcomeActorView();
        assertNotNull(actorView);
        assertNotNull(actorView.getActorRef());
        assertEquals(2, actorView.getMessages().size());
        assertTrue(actorView.getMessages().contains(AkkaMessages.REGISTER));
        assertTrue(actorView.getMessages().contains(AkkaMessages.LOGIN));
        assertEquals(2, actorView.getMailboxes().getComponentCount());

    }

}