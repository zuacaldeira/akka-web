package actors.mvc;

import actors.business.AbstractActorTest;
import actors.business.Supervisor;
import actors.messages.ControlMessage;
import actors.messages.world.LeaveAkkaria;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.testkit.JavaTestKit;
import akka.testkit.TestProbe;
import akka.util.Timeout;
import org.mockito.Mockito;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scala.concurrent.Await;
import views.ui.AkkaUI;
import views.ui.WelcomeUI;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.doNothing;
import static org.testng.Assert.assertEquals;
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
    public void testOnReceive(Props childProps, Class<? extends AkkaUI> classToMock) throws Exception {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef supervisor = getActorSystem().actorOf(Props.create(Supervisor.class), "Supervisor");
                supervisor.tell(childProps, getRef());
                ActorRef child = expectMsgClass(ActorRef.class);
                assertNotNull(child);
                AkkaUI mockUI = Mockito.mock(classToMock);
                doNothing().when(mockUI).leave(Mockito.any(ActorRef.class));
                TestProbe testProbe = TestProbe.apply(getActorSystem());
                testProbe.watch(child);
                Timeout t = new Timeout(5, TimeUnit.SECONDS);
                assertEquals(Await.result(Patterns.ask(child, new LeaveAkkaria(mockUI, ControlMessage.CANCELLED), t), t.duration()), ControlMessage.CANCELLED);
            }
        };
    }

    @Test
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
                {Props.create(WelcomeActor.class), WelcomeUI.class},
                {Props.create(RegisterActor.class), WelcomeUI.class},
                {Props.create(LoginActor.class), WelcomeUI.class},
        };
    }
}