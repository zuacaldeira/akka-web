package actors.business;


import actors.messages.AkkaMessage;
import actors.messages.LoginMessage;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import akka.testkit.TestProbe;
import graphs.Neo4jSessionFactory;
import graphs.entities.Account;
import graphs.entities.Registration;
import graphs.entities.User;
import org.apache.commons.lang3.RandomUtils;
import org.neo4j.ogm.session.Session;
import org.testng.annotations.Test;

/**
 * Created by zua on 30.08.16.
 */
public class LoginActorTest extends AbstractActorTest {


    @Test(dataProvider = "validLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testValidLogin(LoginMessage message) throws Exception {
        seed(message.getUsername(), message.getPassword());
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef loginActor = createActor(LoginActor.class);
                loginActor.tell(message, getRef());
                expectMsgAnyOf(AkkaMessage.LOGIN_SUCCESSFUL);
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
                expectMsgAnyOf(AkkaMessage.LOGIN_INVALID);
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
                expectMsgAnyOf(AkkaMessage.LOGIN_FAILED);
                //probe.expectTerminated(loginActor, Duration.create("1 minute"));
            }
        };
    }

    @Override
    public void testUnhandled() {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef loginActor = createActor(LoginActor.class);
                loginActor.tell(AkkaMessage.LOGIN_SUCCESSFUL, getRef());
                expectNoMsg();
            }
        };
    }
}