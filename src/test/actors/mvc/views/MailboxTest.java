package actors.mvc.views;

import actors.messages.ControlMessage;
import actors.mvc.WelcomeActor;
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
        String content = ControlMessage.REGISTER.name();
        Mailbox mv = new Mailbox(content);
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
                {new Mailbox(ControlMessage.LOGIN.name()), new Mailbox(ControlMessage.LOGIN.name())}
        };
    }

    @DataProvider(name = "inequals")
    public Object[][] inequals() {
        return new Object[][]{
                {new Mailbox(ControlMessage.LOGIN.name()), new Mailbox(ControlMessage.REGISTER.name())},
                {new Mailbox(ControlMessage.REGISTER.name()), new Mailbox(ControlMessage.UNKNOWN.name())},
                {new Mailbox(ControlMessage.REGISTER.name()), new User()}
        };
    }


}