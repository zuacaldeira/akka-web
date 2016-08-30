package actors.core;

import actors.messages.AkkaMessages;
import actors.messages.LoginMessage;
import actors.messages.RegisterMessage;
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
                welcomeActor.tell(new RegisterMessage("username", "password", "Full Name"), getTestActor());
                expectMsgAnyOf(AkkaMessages.DONE);
            }
        };
    }

    @Test
    public void testLogin() {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef welcomeActor = createActor(WelcomeActor.class);
                welcomeActor.tell(new LoginMessage("username", "password"), getRef());
                expectMsgAnyOf(AkkaMessages.DONE);
            }
        };
    }

}
