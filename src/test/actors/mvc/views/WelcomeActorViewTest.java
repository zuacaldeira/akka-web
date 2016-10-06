package actors.mvc.views;

import actors.messages.ControlMessage;
import actors.mvc.WelcomeActor;
import com.vaadin.ui.HorizontalLayout;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by zua on 31.08.16.
 */
public class WelcomeActorViewTest {

    @Test
    public void testWelcomeActorView() {

        ActorView actorView = ActorsViewFactory.getInstance().getActorView(WelcomeActor.class);
        assertNotNull(actorView);
        assertEquals(2, actorView.getMailboxes().getComponentCount());
        assertNotNull(actorView.getMessage(ControlMessage.REGISTER));
        assertNotNull(actorView.getMessage(ControlMessage.LOGIN));
        assertEquals(2, actorView.getMailboxes().getComponentCount());

    }

    @Test
    public void testRegister() {
        WelcomeActorView wav = new WelcomeActorView();
        assertTrue(wav.getActorContent() instanceof HorizontalLayout);
        assertEquals(wav.getMailboxes().getComponentCount(), 2);
        assertNotNull(wav.getMailbox(ControlMessage.REGISTER));
        wav.getMailbox(ControlMessage.REGISTER).click();
    }

    @Test
    public void testLogin() throws Exception {
        WelcomeActorView wav = new WelcomeActorView();
        assertTrue(wav.getActorContent() instanceof HorizontalLayout);
        assertEquals(wav.getMailboxes().getComponentCount(), 2);
        assertNotNull(wav.getMailbox(ControlMessage.LOGIN));
        wav.getMailbox(ControlMessage.LOGIN).click();
    }

}