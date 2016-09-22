package views.actors;

import actors.messages.AkkaMessage;
import org.testng.annotations.Test;
import views.components.WelcomeUI;
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

        ActorView actorView = ActorsViewFactory.getInstance().getWelcomeActorView();
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);

        assertNotNull(actorView);
        assertNotNull(actorView.getActorRef());
        assertEquals(2, actorView.getMailboxes().getComponentCount());
        assertNotNull(actorView.getMessage(AkkaMessage.REGISTER.name()));
        assertNotNull(actorView.getMessage(AkkaMessage.LOGIN.name()));
        assertEquals(2, actorView.getMailboxes().getComponentCount());

    }

    @Test
    public void testRegister() {
        ActorView actorView = ActorsViewFactory.getInstance().getWelcomeActorView();
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);

        actorView.getActorContent();
        assertNotNull(actorView.getActorContent());
        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(0));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), AkkaMessage.REGISTER.name());

        mailbox.click();
        assertTrue((ui.getContent() instanceof RegisterActorView));
    }

    @Test
    public void testLogin() {
        ActorView actorView = ActorsViewFactory.getInstance().getWelcomeActorView();
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);

        actorView.getActorContent();
        assertNotNull(actorView.getActorContent());
        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(1));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), AkkaMessage.LOGIN.name());

        mailbox.click();
        assertTrue((ui.getContent() instanceof LoginActorView));
    }

}