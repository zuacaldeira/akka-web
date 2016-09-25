package actors.messages;

import actors.mvc.RegisterActor;
import actors.mvc.WelcomeActor;
import akka.actor.ActorRef;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import views.ui.AkkaUI;
import views.ui.WelcomeUI;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 25.09.16.
 */
public class LeaveAkkariaTest {


    @Test(dataProvider = "actorAndStatus")
    public void testLeaveAkkaria(ActorRef actor, AkkaMessage status) {
        LeaveAkkaria leaveAkkaria = new LeaveAkkaria(new WelcomeUI(), status);
        assertTrue(leaveAkkaria.getUi() instanceof WelcomeUI);
        assertEquals(leaveAkkaria.getStatus(), status);

        actor = AkkaUI.createActorRef(WelcomeActor.class, "WA");
        actor.tell(leaveAkkaria, ActorRef.noSender());
    }

    @DataProvider(name = "actorAndStatus")
    public static Object[][] actorAndStatus() {
        return new Object[][] {
                {AkkaUI.createActorRef(WelcomeActor.class, "wa"), AkkaMessage.INVALID},
                {AkkaUI.createActorRef(WelcomeActor.class, "wa"), AkkaMessage.FAILED},
                {AkkaUI.createActorRef(WelcomeActor.class, "wa"), AkkaMessage.SUCCESSFUL},
                {AkkaUI.createActorRef(WelcomeActor.class, "wa"), AkkaMessage.CANCELLED},
                {AkkaUI.createActorRef(RegisterActor.class, "wa"), AkkaMessage.INVALID},
                {AkkaUI.createActorRef(RegisterActor.class, "wa"), AkkaMessage.FAILED},
                {AkkaUI.createActorRef(RegisterActor.class, "wa"), AkkaMessage.SUCCESSFUL},
                {AkkaUI.createActorRef(RegisterActor.class, "wa"), AkkaMessage.CANCELLED},
        };
    }

}