package actors.core;

import actors.messages.AkkaMessages;
import actors.messages.LoginMessage;
import graphs.Neo4jSessionFactory;
import graphs.entities.Login;
import graphs.entities.Registration;
import org.neo4j.ogm.session.Session;

import java.util.Collections;

/**
 * Created by zua on 28.08.16.
 */
public class LoginActor extends MVCUntypedActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        if( message instanceof LoginMessage) {
            login((LoginMessage) message);
            getSender().tell(AkkaMessages.DONE, getSelf());
        }
        else {
            unhandled(message);
        }
    }

    private void login(LoginMessage message) {
        // Create driver
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();

        Registration r = session.queryForObject(Registration.class, getCypherQuery(message), Collections.emptyMap());
        if (r != null) {
            session.save(new Login(r.getUser(), r.getAccount()));
            log.info("Login of [user({})]", message.getUsername());
        }
        else {
            throw new IllegalStateException("Cannot login unregistered user " + message.getUsername());
        }
    }

    private String getCypherQuery(LoginMessage message) {
        String match = "MATCH (u : User) -[r : register]-> (a : Account)";
        String where = " WHERE u.email=" + "'" + message.getUsername() + "'";
        return match + where + " RETURN r";
    }

}
