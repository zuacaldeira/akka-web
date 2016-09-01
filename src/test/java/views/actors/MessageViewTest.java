package views.actors;

import actors.core.WelcomeActor;
import actors.messages.AkkaMessages;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vaadin.ui.Button;
import org.testng.annotations.Test;
import views.ui.LoginForm;
import views.ui.RegisterForm;

import static org.testng.Assert.*;

/**
 * Created by zua on 31.08.16.
 */
public class MessageViewTest {
    @Test
    public void testStructure() throws Exception {
        ActorRef ref = ActorSystem.create("TestAS").actorOf(Props.create(WelcomeActor.class), "MessageViewTestActor");
        String content = AkkaMessages.REGISTER;
        MessageView mv = new MessageView(ref, content);
        assertEquals(ref, mv.getActor());
        assertEquals(content, mv.getContent());
        assertTrue(mv.getListeners(Button.ClickEvent.class).contains(mv));
    }

    @Test
    public void testRegisterButtonClick() throws Exception {
        ActorRef ref = ActorSystem.create("TestAS").actorOf(Props.create(WelcomeActor.class), "MessageViewTestActor");
        String content = AkkaMessages.REGISTER;
        MessageView mv = new MessageView(ref, content);
        try {
            assertNull(mv.getUI());
            mv.buttonClick(null);
        } catch (NullPointerException npx) {
            assertNotNull(mv.getWindow());
            assertTrue(mv.getWindow().getContent() instanceof RegisterForm);
        }
    }

    @Test
    public void testLoginButtonClick() throws Exception {
        ActorRef ref = ActorSystem.create("TestAS").actorOf(Props.create(WelcomeActor.class), "MessageViewTestActor");
        String content = AkkaMessages.LOGIN;
        MessageView mv = new MessageView(ref, content);
        try {
            assertNull(mv.getUI());
            mv.buttonClick(null);
        } catch (NullPointerException npx) {
            assertNotNull(mv.getWindow());
            assertTrue(mv.getWindow().getContent() instanceof LoginForm);
        }
    }
}