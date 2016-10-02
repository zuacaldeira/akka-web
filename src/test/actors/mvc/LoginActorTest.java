package actors.mvc;


import actors.business.Supervisor;
import actors.business.TestDataProvider;
import actors.messages.ControlMessage;
import actors.messages.LoginMessage;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import akka.testkit.TestProbe;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;
import views.ui.WelcomeUI;

/**
 * Created by zua on 30.08.16.
 */
public class LoginActorTest extends MVCActorTest {


    @Test(dataProvider = "validLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testValidLogin(LoginMessage message) throws Exception {
        seed(message.getUsername(), message.getPassword());
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef loginActor = createActor(LoginActor.class);
                loginActor.tell(message, getRef());
                expectNoMsg();
            }
        };
    }

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "invalidLoginMessages")
    public void testInvalidLogin(LoginMessage message) throws Exception {
        new JavaTestKit(getActorSystem()) {
            {
                Props superProps = Props.create(Supervisor.class);
                ActorRef supervisor = getActorSystem().actorOf(superProps, "supervisor" + RandomUtils.nextInt(0,100));
                ActorRef loginActor = createActor(LoginActor.class, supervisor);
                TestProbe probe = new TestProbe(getActorSystem());
                probe.watch(loginActor);
                loginActor.tell(message, getRef());
                expectNoMsg();
                //probe.expectTerminated(loginActor, Duration.create("1 minute"));
            }
        };
    }

    @Test(dataProvider = "failedLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testFailedLogin(LoginMessage message) throws Exception {
        new JavaTestKit(getActorSystem()) {
            {
                Props superProps = Props.create(Supervisor.class);
                ActorRef supervisor = getActorSystem().actorOf(superProps, "supervisor" + RandomUtils.nextInt(0,10));

                ActorRef loginActor = createActor(LoginActor.class, supervisor);

                TestProbe probe = new TestProbe(getActorSystem());
                probe.watch(loginActor);
                loginActor.tell(message, getRef());
                expectMsgAnyOf(ControlMessage.FAILED);
                //probe.expectTerminated(loginActor, Duration.create("1 minute"));
            }
        };
    }

    @Override
    public void testUnhandled() {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef loginActor = createActor(LoginActor.class);
                loginActor.tell(ControlMessage.SUCCESSFUL, getRef());
                expectNoMsg();
            }
        };
    }


    @Override
    public void testEnterAkkaria() {
        super.testEnterAkkaria(WelcomeUI.class, LoginActor.class);
    }

    @Override
    public void testLeaveAkkaria() {
        super.testLeaveAkkaria(WelcomeUI.class, LoginActor.class, ControlMessage.SUCCESSFUL);
    }

}