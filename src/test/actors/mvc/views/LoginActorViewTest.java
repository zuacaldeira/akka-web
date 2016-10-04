package actors.mvc.views;

import actors.Neo4JDatabaseTest;
import actors.business.TestDataProvider;
import actors.messages.ControlMessage;
import actors.messages.LoginMessage;
import actors.messages.world.EnterAkkaria;
import actors.mvc.LoginActor;
import actors.mvc.RegisterActor;
import akka.actor.ActorRef;
import com.vaadin.ui.HorizontalLayout;
import org.testng.annotations.Test;
import views.components.LoginForm;
import views.components.Mailbox;
import views.ui.WelcomeUI;

import static org.testng.Assert.*;

/**
 * Created by zua on 22.09.16.
 */
public class LoginActorViewTest extends Neo4JDatabaseTest {
    @Test
    public void testLoginActorView() {

        ActorView actorView = ActorsViewFactory.getInstance().getActorView(LoginActor.class);
        WelcomeUI ui = new WelcomeUI();
        ActorRef ref = ui.createActorRef(RegisterActor.class, "R");
        ref.tell(new EnterAkkaria(), ui.getMVCActor());

        assertNotNull(actorView);
        assertEquals(2, actorView.getMailboxes().getComponentCount());
        assertNotNull(actorView.getMailbox(ControlMessage.LOGIN));
        assertNotNull(actorView.getMailbox(ControlMessage.CANCELLED));
        assertEquals(2, actorView.getMailboxes().getComponentCount());

    }

    @Test(dataProvider = "validLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testLogin(LoginMessage message) {
        seed(message.getUsername(), message.getPassword());

        ActorView actorView = ActorsViewFactory.getInstance().getActorView(LoginActor.class);
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);

        LoginForm registerForm = (LoginForm) actorView.getActorContent();
        assertNotNull(registerForm);
        registerForm.getEmailField().setValue(message.getUsername());
        registerForm.getPasswordField().setValue(message.getPassword());


        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(1));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), ControlMessage.LOGIN.name());

        mailbox.click();

    }

    @Test
    public void testLoginEmptyForm() {
        LoginActorView actorView = (LoginActorView) ActorsViewFactory.getInstance().getActorView(LoginActor.class);
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(new HorizontalLayout(actorView));

        LoginForm registerForm = (LoginForm) actorView.getActorContent();
        assertNotNull(registerForm);
        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(1));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), ControlMessage.LOGIN.name());

        assertFalse(actorView.isFormEdited());
        mailbox.click();
        assertTrue((ui.getTop() instanceof LoginActorView));
    }

    @Test(dataProvider = "validLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testCancel(LoginMessage message) throws InterruptedException {
        LoginActorView actorView = (LoginActorView) ActorsViewFactory.getInstance().getActorView(LoginActor.class);
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);

        LoginForm registerForm = (LoginForm) actorView.getActorContent();
        assertNotNull(registerForm);
        registerForm.getEmailField().setValue(message.getUsername());
        registerForm.getPasswordField().setValue(message.getPassword());


        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(0));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), ControlMessage.CANCELLED.name());

        mailbox.click();
    }

}