package views.actors;

import actors.Neo4JDatabaseTest;
import actors.business.TestDataProvider;
import actors.messages.AkkaMessage;
import actors.messages.LoginMessage;
import org.testng.annotations.Test;
import views.components.LoginForm;
import views.components.WelcomeUI;
import views.factories.ActorsViewFactory;

import static org.testng.Assert.*;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 22.09.16.
 */
public class LoginActorViewTest extends Neo4JDatabaseTest {
    @Test
    public void testLoginActorView() {

        ActorView actorView = ActorsViewFactory.getInstance().getLoginActorView();
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);

        assertNotNull(actorView);
        assertNotNull(actorView.getActorRef());
        assertEquals(2, actorView.getMailboxes().getComponentCount());
        assertNotNull(actorView.getMessage(AkkaMessage.LOGIN.name()));
        assertNotNull(actorView.getMessage(AkkaMessage.CANCEL.name()));
        assertEquals(2, actorView.getMailboxes().getComponentCount());

    }

    @Test(dataProvider = "validLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testLogin(LoginMessage message) {
        seed(message.getUsername(), message.getPassword());

        ActorView actorView = ActorsViewFactory.getInstance().getLoginActorView();
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);
        ui.setMvcActor(actorView.getActorRef());

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
        ui.setMvcActor(actorView.getActorRef());

        LoginForm registerForm = (LoginForm) actorView.getActorContent();
        assertNotNull(registerForm);
        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(1));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), AkkaMessage.LOGIN.name());

        assertFalse(actorView.isFormEdited());
        mailbox.click();
        assertTrue((ui.getContent() instanceof LoginActorView));
    }

    @Test(dataProvider = "validLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testCancel(LoginMessage message) {
        ActorView actorView = ActorsViewFactory.getInstance().getLoginActorView();
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);
        ui.setMvcActor(actorView.getActorRef());

        LoginForm registerForm = (LoginForm) actorView.getActorContent();
        assertNotNull(registerForm);
        registerForm.getEmailField().setValue(message.getUsername());
        registerForm.getPasswordField().setValue(message.getPassword());


        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(0));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), AkkaMessage.CANCEL.name());

        mailbox.click();
        assertTrue((ui.getContent() instanceof WelcomeActorView));
    }

}