package actors.mvc;


import actors.business.AbstractActorTest;
import actors.business.TestDataProvider;
import actors.messages.ControlMessage;
import actors.messages.RegisterMessage;
import akka.actor.ActorRef;
import akka.testkit.JavaTestKit;
import org.testng.annotations.Test;

/**
 * Created by zua on 30.08.16.
 */
public class RegisterActorTest extends AbstractActorTest {


    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "validRegisterMessages")
    public void testRegister(final RegisterMessage message) throws Exception {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef registerActor = createActor(RegisterActor.class);
                registerActor.tell(message, getRef());
                expectMsgEquals(ControlMessage.SUCCESS);
            }
        };
    }

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "invalidRegisterMessages")
    public void testInvalidRegister(final RegisterMessage message) throws Exception {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef registerActor = createActor(RegisterActor.class);
                registerActor.tell(message, getRef());
                expectNoMsg();
            }
        };
    }
}