package actors.mvc;

import actors.messages.ControlMessage;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import org.testng.annotations.Test;
import views.ui.UserUI;

/**
 * Created by zua on 22.09.16.
 */
public class UserActorTest extends MVCActorTest {

    @Test
    public void testEnterAkkaria() {
        super.testEnterAkkaria(UserUI.class, UserActor.class);
    }

    @Test
    public void testGoToProfile() {
        super.testControlMessage(UserUI.class, UserActor.class, ControlMessage.PROFILE);
    }

    @Test
    public void testGoToProject() {
        super.testControlMessage(UserUI.class, UserActor.class, ControlMessage.PROJECT);
    }

    @Test
    public void testLeaveAkkaria() {
        super.testLeaveAkkaria(UserUI.class, UserActor.class, ControlMessage.SUCCESSFUL);
    }

    @Test
    public void testUnhandled() {
        new JavaTestKit(getActorSystem()){
            {
                ActorRef subject = getActorSystem().actorOf(Props.create(UserActor.class));
                subject.tell("hello", getRef());
                expectNoMsg();
            }
        };
    }
}