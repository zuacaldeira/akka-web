package actors.core;


import actors.messages.AkkaMessages;
import actors.messages.RegisterMessage;
import akka.actor.ActorRef;
import akka.testkit.JavaTestKit;
import org.testng.annotations.Test;

/**
 * Created by zua on 30.08.16.
 */
public class RegisterActorTest extends AbstractActorTest {


    @Test
    public void onReceive() throws Exception {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef registerActor = createActor(RegisterActor.class);
                registerActor.tell(new RegisterMessage("username", "password", "Full name"), getRef());
                expectMsgAnyOf(AkkaMessages.DONE);
            }
        };
    }

}