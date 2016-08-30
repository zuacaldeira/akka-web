package actors.core;

import actors.messages.AkkaMessages;
import actors.messages.LoginMessage;
import akka.actor.Props;
import graphs.Neo4jSessionFactory;
import graphs.entities.Login;
import graphs.entities.Registration;
import org.neo4j.ogm.session.Session;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by zua on 28.08.16.
 */
public class LoginActor extends MVCUntypedActor {

    public static final akka.actor.Props PROPS = Props.create(LoginActor.class);
    public static final String NAME = LoginActor.class.getSimpleName();
    public static final List<String> MESSAGES = Arrays.asList(AkkaMessages.LOGIN);

    @Override
    public void onReceive(Object message) throws Throwable {
        if( message instanceof LoginMessage) {
            login((LoginMessage) message);
            getSender().tell(AkkaMessages.DONE, getSelf());
        }
    }

    private void login(LoginMessage message) {
        // Create driver
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();

        String match = "MATCH (u : User) -[r : register]-> (a : Account)";
        String where = " WHERE a.email=" + "'" + message.getUsername() + "'";
        String cypher = match + where + " RETURN r";

        session.beginTransaction();
        Registration r = session.queryForObject(Registration.class, cypher, Collections.emptyMap());
        if (r != null) {
            session.save(new Login(r.getUser(), r.getAccount()));
            session.getTransaction().commit();
            log.info("Login of [user({})]", message.getUsername());
        } else {
            throw new IllegalStateException("Cannot login unregistered user " + message.getUsername());
        }
    }

}
