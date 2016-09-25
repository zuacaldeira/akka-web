package actors.business;


import actors.messages.RegisterMessage;
import actors.mvc.RegisterActor;
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
                expectNoMsg();
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

    @Override
    public void testUnhandled() {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef registerActor = createActor(RegisterActor.class);
                registerActor.tell("DO_NOT_HANDLE", getRef());
                expectNoMsg();
            }
        };
    }
}