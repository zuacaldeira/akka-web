package views.actors;

import actors.core.LoginActor;
import actors.core.WelcomeActor;
import actors.messages.AkkaMessages;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import graphs.entities.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
        assertEquals(content, mv.getMessage());
    }


    @Test(dataProvider = "equals")
    public void testEquals(MessageView a, MessageView b) throws Exception {
        assertTrue(a.equals(b));
    }

    @Test(dataProvider = "equals")
    public void testHashCode(MessageView a, MessageView b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test(dataProvider = "inequals")
    public void testInequals(MessageView a, Object b) throws Exception {
        assertFalse(a.equals(b));
    }


    @DataProvider(name = "equals")
    public Object[][] equals() {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(WelcomeActor.class));
        return new Object[][]{
                {new MessageView(actor, AkkaMessages.LOGIN), new MessageView(actor, AkkaMessages.LOGIN)}
        };
    }

    @DataProvider(name = "inequals")
    public Object[][] inequals() {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(WelcomeActor.class));
        ActorRef actor2 = ActorSystem.create().actorOf(Props.create(LoginActor.class));
        return new Object[][]{
                {new MessageView(actor, AkkaMessages.LOGIN), new MessageView(actor2, AkkaMessages.LOGIN)},
                {new MessageView(actor2, AkkaMessages.REGISTER), new MessageView(actor, AkkaMessages.UNKNOWN)},
                {new MessageView(actor2, AkkaMessages.REGISTER), new User()}
        };
    }


}