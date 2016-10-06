package actors.mvc;


import actors.business.Supervisor;
import actors.business.TestDataProvider;
import actors.messages.ControlMessage;
import actors.messages.LoginMessage;
import actors.messages.RegisterMessage;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import akka.testkit.TestProbe;
import graphs.entities.nodes.User;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;

/**
 * Created by zua on 30.08.16.
 */
public class LoginActorTest extends MVCActorTest {

    @Test(dataProvider = "validLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testValidLogin(LoginMessage message) throws Exception {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef registerActor = createActor(RegisterActor.class);
                Object m = new RegisterMessage(message.getUsername(), message.getPassword(), "Full Name Of Mine");
                //Patterns.ask(registerActor, m, new Timeout(5, TimeUnit.SECONDS));
                registerActor.tell(m, getRef());
                expectMsgEquals(ControlMessage.SUCCESS);

                ActorRef loginActor = createActor(LoginActor.class);
                loginActor.tell(message, getRef());
                expectMsgEquals(ControlMessage.SUCCESS);
            }
        };
/*
new JavaTestKit(getActorSystem()) {
            {
                ActorRef loginActor = createActor(LoginActor.class);
                loginActor.tell(message, getRef());
                expectMsgEquals(ControlMessage.SUCCESS);
            }
        };
        */
    }

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "invalidLoginMessages")
    public void testInvalidLogin(LoginMessage message) throws Exception {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef loginActor = createActor(LoginActor.class);
                loginActor.tell(message, getRef());
                expectNoMsg();
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
                expectMsgAnyOf(ControlMessage.FAILURE);
                //probe.expectTerminated(loginActor, Duration.create("1 minute"));
            }
        };
    }


    @Override
    public void testUnhandled() {
        super.testUnhandled(LoginActor.class);
    }

    @Override
    public void testEnterAkkaria() {
        super.testEnterAkkaria(createActor(LoginActor.class));
    }

    @Override
    public void testLeaveAkkaria() {
        super.testLeaveAkkaria(createActor(UserActor.class, new User()), ControlMessage.SUCCESS);
    }

}