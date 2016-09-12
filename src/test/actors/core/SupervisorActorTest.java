package actors.core;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 01.09.16.
 */
public class SupervisorActorTest extends AbstractActorTest {

    //@Test(dataProvider = "dataProviderOnReceive")
    public void testSupervisorStrategy(Object message) throws Exception {
        new JavaTestKit(getActorSystem()) {
            {
                // TODO: Not Yet Implemented
            }
        };

    }

    @Test(dataProvider = "dataProvider")
    public void testOnReceive(Object message) throws Exception {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef supervisor = getActorSystem().actorOf(Props.create(Supervisor.class), "Supervisor");
                supervisor.tell(message, getRef());
                ActorRef child = expectMsgClass(ActorRef.class);
                assertNotNull(child);
            }
        };
    }

    @Override
    public void testUnhandled() {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef supervisor = getActorSystem().actorOf(Props.create(Supervisor.class), "Supervisor");
                supervisor.tell("Hello", getRef());
                expectNoMsg();
            }
        };
    }


    // DATA PROVIDER METHODS
    @DataProvider(name = "dataProvider")
    public Object[][] dataProviderOnReceive() {
        return new Object[][]{
                {Props.create(WelcomeActor.class)},
                {Props.create(RegisterActor.class)},
                {Props.create(LoginActor.class)}
        };
    }
}