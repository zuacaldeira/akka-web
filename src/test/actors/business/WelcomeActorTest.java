package actors.business;

import actors.messages.AkkaMessage;
import actors.mvc.WelcomeActor;
import akka.actor.ActorRef;
import akka.testkit.JavaTestKit;
import org.testng.annotations.Test;

/**
 * Created by zua on 28.08.16.
 */

public class WelcomeActorTest extends AbstractActorTest {

    @Test
    public void testRegister() {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef welcomeActor = createActor(WelcomeActor.class);
                welcomeActor.tell(AkkaMessage.REGISTER, getRef());
                expectNoMsg();
            }
        };
    }

    @Test
    public void testLogin() {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef welcomeActor = createActor(WelcomeActor.class);
                welcomeActor.tell(AkkaMessage.LOGIN, getRef());
                expectNoMsg();
            }
        };
    }

    @Override
    public void testUnhandled() {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef welcomeActor = createActor(WelcomeActor.class);
                welcomeActor.tell("DO_NOT_HANDLE", getRef());
                expectNoMsg();
            }
        };
    }
}
