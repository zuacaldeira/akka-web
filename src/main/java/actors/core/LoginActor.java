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
        }
        else {
            unhandled(message);
        }
    }

    private void login(LoginMessage message) throws IllegalLoginException {
        // Create driver
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();

        Registration r = session.queryForObject(Registration.class, getCypherQuery(message), Collections.emptyMap());
        if (r != null) {
            session.save(new Login(r.getUser(), r.getAccount()));
            // Notifies the sender
            getSender().tell(AkkaMessages.DONE, getSelf());
            // Log success
            log.info("Success: Login of [user({})]", message.getUsername());
        }
        else {
            IllegalLoginException e = new IllegalLoginException(message.getUsername());
            // Notifies the sender
            getSender().tell(e, getSelf());
            // Fail for supervision strategy
            throw e;
        }
    }

    private String getCypherQuery(LoginMessage message) {
        String match = "MATCH (u : User) -[r : register]-> (a : Account)";
        String where = " WHERE u.email=" + "'" + message.getUsername() + "'";
        return match + where + " RETURN r";
    }

}
