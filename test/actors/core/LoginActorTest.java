package actors.core;


import actors.messages.AkkaMessages;
import actors.messages.LoginMessage;
import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.testkit.JavaTestKit;
import akka.testkit.TestProbe;
import graphs.Neo4jSessionFactory;
import graphs.entities.Account;
import graphs.entities.Registration;
import graphs.entities.User;
import org.neo4j.ogm.session.Session;
import org.testng.annotations.Test;

/**
 * Created by zua on 30.08.16.
 */
public class LoginActorTest extends AbstractActorTest {


    private String USERNAME = "username";
    private String PASSWORD = "password";

    @Test
    public void testValidLogin() throws Exception {
        seed();
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef loginActor = createActor(LoginActor.class);
                loginActor.tell(new LoginMessage(USERNAME, PASSWORD), getRef());
                expectMsgAnyOf(AkkaMessages.DONE);
            }
        };
    }

    @Test
    public void testInvalidLogin() throws Exception {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef loginActor = createActor(LoginActor.class);

                TestProbe probe = new TestProbe(getActorSystem());
                probe.watch(loginActor);
                loginActor.tell(new LoginMessage(USERNAME, PASSWORD), getRef());
                probe.expectMsgClass(Terminated.class);
            }
        };
    }

    @Override
    public void testUnhandled() {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef loginActor = createActor(LoginActor.class);
                loginActor.tell(AkkaMessages.DONE, getRef());
                expectNoMsg();
            }
        };
    }

    private void seed() {
        User u = new User(USERNAME, PASSWORD);
        Account a = new Account(USERNAME, PASSWORD);
        Registration r = new Registration(u, a);
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.beginTransaction();
        session.save(r, 2);
        session.getTransaction().commit();
    }

}