package views.actors;

import actors.messages.AkkaMessages;
import org.testng.annotations.Test;
import views.factories.ActorsViewFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 31.08.16.
 */
public class WelcomeActorViewTest {

    @Test
    public void testWelcomeActorView() {
        ActorView actorView = ActorsViewFactory.getInstance().getWelcomeActorView();
        assertNotNull(actorView);
        assertNotNull(actorView.getActorRef());
        assertEquals(2, actorView.getMailboxes().getComponentCount());
        assertNotNull(actorView.getMessage(AkkaMessages.REGISTER));
        assertNotNull(actorView.getMessage(AkkaMessages.LOGIN));
        assertEquals(2, actorView.getMailboxes().getComponentCount());

    }

}