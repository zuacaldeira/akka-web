package views.actors;

import actors.business.LoginActor;
import actors.messages.AkkaMessage;
import actors.mvc.WelcomeMVCActor;
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
        ActorRef ref = ActorSystem.create("TestAS").actorOf(Props.create(WelcomeMVCActor.class), "MessageViewTestActor");
        String content = AkkaMessage.REGISTER.name();
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
        ActorRef actor = ActorSystem.create().actorOf(Props.create(WelcomeMVCActor.class));
        return new Object[][]{
                {new Mailbox(actor, AkkaMessage.LOGIN.name()), new Mailbox(actor, AkkaMessage.LOGIN.name())}
        };
    }

    @DataProvider(name = "inequals")
    public Object[][] inequals() {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(WelcomeMVCActor.class));
        ActorRef actor2 = ActorSystem.create().actorOf(Props.create(LoginActor.class));
        return new Object[][]{
                {new Mailbox(actor, AkkaMessage.LOGIN.name()), new Mailbox(actor2, AkkaMessage.LOGIN.name())},
                {new Mailbox(actor2, AkkaMessage.REGISTER.name()), new Mailbox(actor, AkkaMessage.UNKNOWN.name())},
                {new Mailbox(actor2, AkkaMessage.REGISTER.name()), new User()}
        };
    }


}