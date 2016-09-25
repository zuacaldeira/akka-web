package actors.mvc.views;

import actors.messages.AkkaMessage;
import com.vaadin.ui.HorizontalLayout;
import org.testng.annotations.Test;
import views.factories.ActorsViewFactory;
import views.ui.WelcomeUI;

import static org.testng.Assert.*;

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
        assertEquals(2, actorView.getMailboxes().getComponentCount());
        assertNotNull(actorView.getMessage(AkkaMessage.REGISTER));
        assertNotNull(actorView.getMessage(AkkaMessage.LOGIN));
        assertEquals(2, actorView.getMailboxes().getComponentCount());

    }

    @Test
    public void testRegister() {
        WelcomeUI ui = new WelcomeUI();
        WelcomeActorView wav = new WelcomeActorView();
        ui.setContent(wav);

        assertTrue(wav.getActorContent() instanceof HorizontalLayout);
        assertEquals(wav.getMailboxes().getComponentCount(), 2);
        wav.getMailbox(AkkaMessage.REGISTER).click();
    }

    @Test
    public void testLogin() throws Exception {
        WelcomeUI ui = new WelcomeUI();
        WelcomeActorView wav = new WelcomeActorView();
        ui.setContent(wav);

        assertTrue(wav.getActorContent() instanceof HorizontalLayout);
        assertEquals(wav.getMailboxes().getComponentCount(), 2);
        wav.getMailbox(AkkaMessage.LOGIN).click();
    }

}