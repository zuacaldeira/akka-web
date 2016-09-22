package views.actors;

import actors.business.AbstractActorTest;
import actors.business.TestDataProvider;
import actors.messages.AkkaMessage;
import actors.messages.RegisterMessage;
import org.testng.annotations.Test;
import views.components.RegisterForm;
import views.components.WelcomeUI;
import views.factories.ActorsViewFactory;

import static org.testng.Assert.*;

/**
 * Created by zua on 22.09.16.
 */
public class RegisterActorViewTest extends AbstractActorTest {
    @Test
    public void testRegisterActorView() {

        ActorView actorView = ActorsViewFactory.getInstance().getRegisterActorView();
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);

        assertNotNull(actorView);
        assertNotNull(actorView.getActorRef());
        assertEquals(2, actorView.getMailboxes().getComponentCount());
        assertNotNull(actorView.getMessage(AkkaMessage.REGISTER.name()));
        assertNotNull(actorView.getMessage(AkkaMessage.CANCEL.name()));
        assertEquals(2, actorView.getMailboxes().getComponentCount());

    }

    @Test(dataProvider = "validRegisterMessages", dataProviderClass = TestDataProvider.class)
    public void testRegister(RegisterMessage message) {
        ActorView actorView = ActorsViewFactory.getInstance().getRegisterActorView();
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);

        RegisterForm registerForm = (RegisterForm) actorView.getActorContent();
        assertNotNull(registerForm);
        registerForm.getEmailField().setValue(message.getEmail());
        registerForm.getPasswordField().setValue(message.getPassword());
        registerForm.getPasswordConfirmationField().setValue(message.getPassword());
        registerForm.getFullName().setValue(message.getFullname());


        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(1));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), AkkaMessage.REGISTER.name());

        mailbox.click();
        assertTrue((ui.getContent() instanceof LoginActorView));
    }

    @Test(dataProvider = "validRegisterMessages", dataProviderClass = TestDataProvider.class)
    public void testRegisterEmptyForm(RegisterMessage message) {
        RegisterActorView actorView = ActorsViewFactory.getInstance().getRegisterActorView();
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);

        RegisterForm registerForm = (RegisterForm) actorView.getActorContent();
        assertNotNull(registerForm);
        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(1));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), AkkaMessage.REGISTER.name());

        assertFalse(actorView.isFormEdited());
        mailbox.click();
        assertTrue((ui.getContent() instanceof RegisterActorView));
    }

    @Test(dataProvider = "validRegisterMessages", dataProviderClass = TestDataProvider.class)
    public void testCancel(RegisterMessage message) {
        ActorView actorView = ActorsViewFactory.getInstance().getRegisterActorView();
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);

        RegisterForm registerForm = (RegisterForm) actorView.getActorContent();
        assertNotNull(registerForm);
        registerForm.getEmailField().setValue(message.getEmail());
        registerForm.getPasswordField().setValue(message.getPassword());
        registerForm.getPasswordConfirmationField().setValue(message.getPassword());
        registerForm.getFullName().setValue(message.getFullname());


        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(0));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), AkkaMessage.CANCEL.name());

        mailbox.click();
        assertTrue((ui.getContent() instanceof WelcomeActorView));
    }

    @Override
    public void testUnhandled() {

    }
}