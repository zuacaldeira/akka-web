package actors.mvc.views;

import actors.business.TestDataProvider;
import actors.messages.ControlMessage;
import actors.messages.RegisterMessage;
import actors.mvc.RegisterActor;
import com.vaadin.ui.HorizontalLayout;
import org.testng.annotations.Test;
import views.components.RegisterForm;
import views.ui.WelcomeUI;

import static org.testng.Assert.*;

/**
 * Created by zua on 22.09.16.
 */
public class RegisterActorViewTest {
    @Test
    public void testRegisterActorView() {

        ActorView actorView = ActorsViewFactory.getInstance().getActorView(RegisterActor.class);
        WelcomeUI ui = new WelcomeUI();
        ui.setContent(actorView);

        assertNotNull(actorView);
        assertEquals(2, actorView.getMailboxes().getComponentCount());
        assertNotNull(actorView.getMailbox(ControlMessage.REGISTER));
        assertNotNull(actorView.getMailbox(ControlMessage.CANCELLED));
        assertEquals(2, actorView.getMailboxes().getComponentCount());

    }

    @Test(dataProvider = "validRegisterMessages", dataProviderClass = TestDataProvider.class)
    public void testRegister(RegisterMessage message) throws InterruptedException {
        ActorView actorView = ActorsViewFactory.getInstance().getActorView(RegisterActor.class);
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
        assertEquals( mailbox.getMessage(), ControlMessage.REGISTER.name());

        mailbox.click();
        //assertTrue((ui.getContent() instanceof LoginActorView));
    }

    @Test(dataProvider = "validRegisterMessages", dataProviderClass = TestDataProvider.class)
    public void testRegisterEmptyForm(RegisterMessage message) {
        RegisterActorView actorView = (RegisterActorView) ActorsViewFactory.getInstance().getActorView(RegisterActor.class);
        WelcomeUI ui = new WelcomeUI();

        ui.setContent(new HorizontalLayout(actorView));

        RegisterForm registerForm = (RegisterForm) actorView.getActorContent();
        assertNotNull(registerForm);
        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(1));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), ControlMessage.REGISTER.name());

        assertFalse(actorView.isFormEdited());
        mailbox.click();
    }

    @Test(dataProvider = "validRegisterMessages", dataProviderClass = TestDataProvider.class)
    public void testCancel(RegisterMessage message) {
        ActorView actorView = ActorsViewFactory.getInstance().getActorView(RegisterActor.class);
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
        assertEquals( mailbox.getMessage(), ControlMessage.CANCELLED.name());

        mailbox.click();
//        assertTrue((ui.getContent() instanceof WelcomeActorView));
    }

}