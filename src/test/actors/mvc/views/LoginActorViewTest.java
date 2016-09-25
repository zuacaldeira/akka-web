package actors.mvc.views;

import actors.Neo4JDatabaseTest;
import actors.business.TestDataProvider;
import actors.messages.AkkaMessage;
import actors.messages.EnterAkkaria;
import actors.messages.LoginMessage;
import actors.mvc.RegisterActor;
import akka.actor.ActorRef;
import org.testng.annotations.Test;
import views.components.LoginForm;
import views.factories.ActorsViewFactory;
import views.ui.WelcomeUI;

import static org.testng.Assert.*;

/**
 * Created by zua on 22.09.16.
 */
public class LoginActorViewTest extends Neo4JDatabaseTest {
    @Test
    public void testLoginActorView() {

        ActorView actorView = ActorsViewFactory.getInstance().getLoginActorView();
        WelcomeUI ui = new WelcomeUI();
        ActorRef ref = ui.createActorRef(RegisterActor.class, "R");
        ref.tell(new EnterAkkaria(ui), ui.getMVCActor());

        assertNotNull(actorView);
        assertEquals(2, actorView.getMailboxes().getComponentCount());
        assertNotNull(actorView.getMailbox(AkkaMessage.LOGIN));
        assertNotNull(actorView.getMailbox(AkkaMessage.CANCEL));
        assertEquals(2, actorView.getMailboxes().getComponentCount());

    }

    @Test(dataProvider = "validLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testLogin(LoginMessage message) {
        seed(message.getUsername(), message.getPassword());

        ActorView actorView = ActorsViewFactory.getInstance().getLoginActorView();
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);

        LoginForm registerForm = (LoginForm) actorView.getActorContent();
        assertNotNull(registerForm);
        registerForm.getEmailField().setValue(message.getUsername());
        registerForm.getPasswordField().setValue(message.getPassword());


        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(1));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), AkkaMessage.LOGIN.name());

        mailbox.click();

    }

    @Test
    public void testLoginEmptyForm() {
        LoginActorView actorView = ActorsViewFactory.getInstance().getLoginActorView();
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);

        LoginForm registerForm = (LoginForm) actorView.getActorContent();
        assertNotNull(registerForm);
        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(1));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), AkkaMessage.LOGIN.name());

        assertFalse(actorView.isFormEdited());
        mailbox.click();
        assertTrue((ui.getTop() instanceof LoginActorView));
    }

    @Test(dataProvider = "validLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testCancel(LoginMessage message) throws InterruptedException {
        ActorView actorView = ActorsViewFactory.getInstance().getLoginActorView();
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);

        LoginForm registerForm = (LoginForm) actorView.getActorContent();
        assertNotNull(registerForm);
        registerForm.getEmailField().setValue(message.getUsername());
        registerForm.getPasswordField().setValue(message.getPassword());


        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(0));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), AkkaMessage.CANCEL.name());

        mailbox.click();
    }

}