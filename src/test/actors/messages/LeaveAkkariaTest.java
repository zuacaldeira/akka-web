package actors.messages;

import actors.messages.world.LeaveAkkaria;
import actors.mvc.RegisterActor;
import actors.mvc.WelcomeActor;
import akka.actor.ActorRef;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import views.ui.WelcomeUI;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 25.09.16.
 */
public class LeaveAkkariaTest {


    @Test(dataProvider = "actorAndStatus")
    public void testLeaveAkkaria(ActorRef actor, ControlMessage status) {
        LeaveAkkaria leaveAkkaria = new LeaveAkkaria(new WelcomeUI(), status);
        assertTrue(leaveAkkaria.getUi() instanceof WelcomeUI);
        assertEquals(leaveAkkaria.getResult(), status);

        actor = new WelcomeUI().createActorRef(WelcomeActor.class, "WA");
        actor.tell(leaveAkkaria, ActorRef.noSender());
    }

    @DataProvider(name = "actorAndStatus")
    public static Object[][] actorAndStatus() {
        return new Object[][] {
                {new WelcomeUI().createActorRef(WelcomeActor.class, "wa"), ControlMessage.INVALID},
                {new WelcomeUI().createActorRef(WelcomeActor.class, "wa"), ControlMessage.FAILURE},
                {new WelcomeUI().createActorRef(WelcomeActor.class, "wa"), ControlMessage.SUCCESS},
                {new WelcomeUI().createActorRef(WelcomeActor.class, "wa"), ControlMessage.CANCELLED},
                {new WelcomeUI().createActorRef(RegisterActor.class, "wa"), ControlMessage.INVALID},
                {new WelcomeUI().createActorRef(RegisterActor.class, "wa"), ControlMessage.FAILURE},
                {new WelcomeUI().createActorRef(RegisterActor.class, "wa"), ControlMessage.SUCCESS},
                {new WelcomeUI().createActorRef(RegisterActor.class, "wa"), ControlMessage.CANCELLED},
        };
    }

}