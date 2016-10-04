package actors.mvc;

import actors.messages.ControlMessage;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import graphs.entities.User;
import org.testng.annotations.Test;
import views.ui.WelcomeUI;

/**
 * Created by zua on 22.09.16.
 */
public class UserActorTest extends MVCActorTest {

    @Test
    public void testEnterAkkaria() {
        super.testEnterAkkaria(createActor(UserActor.class, new User()));
    }

    @Test
    public void testGoToProfile() {
        super.testControlMessage(createActor(UserActor.class, new User()), ControlMessage.PROFILE);
    }

    @Test
    public void testGoToProject() {
        super.testControlMessage(createActor(UserActor.class, new User()), ControlMessage.PROJECT);
    }

    @Test
    public void testLeaveAkkaria() {
        super.testLeaveAkkaria(createActor(UserActor.class, new User()), ControlMessage.SUCCESS);
    }

    @Test
    public void testUnhandled() {
        new JavaTestKit(getActorSystem()){
            {
                ActorRef subject = getActorSystem().actorOf(Props.create(UserActor.class, new WelcomeUI(), new User()));
                subject.tell("hello", getRef());
                expectNoMsg();
            }
        };
    }
}