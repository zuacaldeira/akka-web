package actors.core;

import actors.messages.AkkaMessages;
import akka.actor.ActorRef;
import akka.testkit.JavaTestKit;
import org.testng.annotations.Test;
import views.actors.MVCMessage;
import views.actors.WelcomeActorView;

/**
 * Created by zua on 28.08.16.
 */

public class WelcomeActorTest extends AbstractActorTest {

    @Test
    public void testRegister() {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef welcomeActor = createActor(WelcomeActor.class);
                welcomeActor.tell(new MVCMessage(new WelcomeActorView(), AkkaMessages.REGISTER), getRef());
                expectMsgAnyOf(AkkaMessages.DONE);
            }
        };
    }

    @Test
    public void testLogin() {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef welcomeActor = createActor(WelcomeActor.class);
                welcomeActor.tell(new MVCMessage(new WelcomeActorView(), AkkaMessages.LOGIN), getRef());
                expectMsgAnyOf(AkkaMessages.DONE);
            }
        };
    }

    @Override
    public void testUnhandled() {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef welcomeActor = createActor(WelcomeActor.class);
                welcomeActor.tell(AkkaMessages.DO_NOT_HANDLE, getRef());
                expectNoMsg();
            }
        };
    }
}
