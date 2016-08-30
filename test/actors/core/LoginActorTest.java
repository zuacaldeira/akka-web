package actors.core;


import actors.messages.AkkaMessages;
import actors.messages.LoginMessage;
import akka.actor.ActorRef;
import akka.testkit.JavaTestKit;
import org.testng.annotations.Test;

/**
 * Created by zua on 30.08.16.
 */
public class LoginActorTest extends AbstractActorTest {


    @Test
    public void onReceive() throws Exception {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef loginActor = createActor(LoginActor.class);
                loginActor.tell(new LoginMessage("username", "password"), getRef());
                expectMsgAnyOf(AkkaMessages.DONE);
            }
        };
    }

}