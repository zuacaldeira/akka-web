package actors.mvc.views;

import actors.business.AbstractActorTest;
import actors.business.TestDataProvider;
import actors.messages.ControlMessage;
import actors.messages.LoginMessage;
import actors.mvc.LoginActor;
import actors.mvc.MVCActor;
import akka.actor.ActorRef;
import com.vaadin.ui.HorizontalLayout;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.Test;
import views.components.LoginForm;
import views.components.Mailbox;
import views.ui.AkkaMocks;
import views.ui.WelcomeUI;

import static org.mockito.Matchers.any;
import static org.testng.Assert.*;

/**
 * Created by zua on 22.09.16.
 */
public class LoginAsActorViewTest extends AbstractActorTest  {
    @Test
    public void testLoginActorView() {
        LoginActorView view = new LoginActorView();
        assertFalse(view.isFormEdited());
        assertNotNull(view.getActorContent());
        assertNotNull(view.getMailbox(ControlMessage.CANCELLED));
        assertNotNull(view.getMailbox(ControlMessage.LOGIN));
        assertTrue(view.getMailbox(ControlMessage.CANCELLED).isEnabled());
        assertFalse(view.getMailbox(ControlMessage.LOGIN).isEnabled());
    }

    @Test(dataProvider = "validLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testLogin(LoginMessage message) {
        seed(message.getUsername(), message.getPassword());

        LoginActorView actorView = (LoginActorView) ActorsViewFactory.getInstance().getActorView(LoginActor.class);
        ActorRef loginActor = createActor(LoginActor.class);

        WelcomeUI ui = AkkaMocks.getWelcomeMockUI();
        Mockito.doAnswer(
                new Answer<Void>(){
                    @Override
                    public Void answer(InvocationOnMock invocation) throws Throwable {
                        if(ui.getContent() != null) {
                            System.out.println("MOCKED ENTER METHOD");
                            ui.getContent().enter((ActorRef) invocation.getArguments()[0], (Class<? extends MVCActor>) invocation.getArguments()[1]);
                        }

                        return null;
                    }
                }).when(ui).enter(any(ActorRef.class), Mockito.eq(LoginActor.class));


        ui.enter(loginActor, LoginActor.class);

        LoginForm loginForm = (LoginForm) actorView.getActorContent();
        assertNotNull(loginForm);

        loginForm.getEmailField().setValue(message.getUsername());
        loginForm.getPasswordField().setValue(message.getPassword());


        Mailbox mailbox = ((Mailbox) actorView.getMailboxes().getMailbox(1));
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), ControlMessage.LOGIN.name());

        mailbox.click();
        System.out.println("View class is: " + ui.getContent().getCurrentView().getClass());
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
        assertNotNull( mailbox );
        assertNotNull( mailbox.getMessage() );
        assertEquals( mailbox.getMessage(), ControlMessage.CANCELLED.name());

        mailbox.click();
    }

}