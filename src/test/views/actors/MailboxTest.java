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
public class MailboxTest {
    @Test
    public void testStructure() throws Exception {
        ActorRef ref = ActorSystem.create("TestAS").actorOf(Props.create(WelcomeActor.class), "MessageViewTestActor");
        String content = AkkaMessages.REGISTER;
        Mailbox mv = new Mailbox(ref, content);
        assertEquals(ref, mv.getActor());
        assertEquals(content, mv.getMessage());
    }


    @Test(dataProvider = "equals")
    public void testEquals(Mailbox a, Mailbox b) throws Exception {
        assertTrue(a.equals(b));
    }

    @Test(dataProvider = "equals")
    public void testHashCode(Mailbox a, Mailbox b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test(dataProvider = "inequals")
    public void testInequals(Mailbox a, Object b) throws Exception {
        assertFalse(a.equals(b));
    }


    @DataProvider(name = "equals")
    public Object[][] equals() {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(WelcomeActor.class));
        return new Object[][]{
                {new Mailbox(actor, AkkaMessages.LOGIN), new Mailbox(actor, AkkaMessages.LOGIN)}
        };
    }

    @DataProvider(name = "inequals")
    public Object[][] inequals() {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(WelcomeActor.class));
        ActorRef actor2 = ActorSystem.create().actorOf(Props.create(LoginActor.class));
        return new Object[][]{
                {new Mailbox(actor, AkkaMessages.LOGIN), new Mailbox(actor2, AkkaMessages.LOGIN)},
                {new Mailbox(actor2, AkkaMessages.REGISTER), new Mailbox(actor, AkkaMessages.UNKNOWN)},
                {new Mailbox(actor2, AkkaMessages.REGISTER), new User()}
        };
    }


}