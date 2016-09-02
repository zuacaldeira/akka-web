package views.ui;

import actors.core.LoginActor;
import actors.core.WelcomeActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by zua on 01.09.16.
 */
public class ActorFormTest {
    @Test(dataProvider = "equals")
    public void testEquals(ActorForm a, ActorForm b) throws Exception {
        assertTrue(a.equals(b));
    }

    @Test(dataProvider = "equals")
    public void testHashCode(ActorForm a, ActorForm b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test(dataProvider = "inequals")
    public void testInequals(ActorForm a, ActorForm b) throws Exception {
        assertFalse(a.equals(b));
    }


    @DataProvider(name = "equals")
    public Object[][] equals() {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(WelcomeActor.class));
        return new Object[][]{
                {new ActorForm(actor), new ActorForm(actor)}
        };
    }

    @DataProvider(name = "inequals")
    public Object[][] inequals() {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(WelcomeActor.class));
        ActorRef actor2 = ActorSystem.create().actorOf(Props.create(LoginActor.class));
        return new Object[][]{
                {new ActorForm(actor), new ActorForm(actor2)}, {new ActorForm(actor), null}
        };
    }
}
