package actors.core;

import actors.core.exceptions.IllegalLoginException;
import actors.core.exceptions.MultipleRegistrationException;
import actors.core.exceptions.NoSuchRegistrationException;
import actors.core.exceptions.UnexpectedException;
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

    /**
     * Store a login relationship between a user an her's account.
     *
     * @param message A message with user's credentials
     * @throws UnexpectedException If in presence of a non business exception (like
     * exceptions connecting to the database).
     * @throws IllegalLoginException If can't find a registration for the user
     */
    private void login(LoginMessage message) {
        // Create session
        Session session = getNeo4jSession();
        // Retrieve a registration relationship with this user
        Registration registration = getRegistration(session, message);
        // Save the user
        createUser(session, registration);
        // Notifies the user
        acknowledgeSender(AkkaMessages.DONE);
    }

    private void acknowledgeSender(String done) {
        getSender().tell(done, getSelf());
    }

    private void createUser(Session session, Registration registration) {
        session.save(new Login(registration.getUser(), registration.getAccount()));
    }

    private Registration getRegistration(Session session, LoginMessage message) {
        // Tries to find a unique registration for this user
        try {
            Registration registration =  session.queryForObject(Registration.class, getCypherQuery(message.getUsername()), Collections.emptyMap());
            if(registration != null) {
                return registration;
            }
        } catch (RuntimeException ex) { // duplications detected
            logException(new MultipleRegistrationException(message.getUsername()));
        }
        // No registration found
        logException(new NoSuchRegistrationException(message.getUsername()));
        return null;
    }

    private void logException(IllegalLoginException e) {
        log.error("Illegal login attempt: ", e.getMessage());
        throw e;
    }

    private Session getNeo4jSession() {
        try {
            return Neo4jSessionFactory.getInstance().getNeo4jSession();
        } catch (Exception e) {
            throw new UnexpectedException("Problems creating a Neo4j Session", e);
        }
    }

    private String getCypherQuery(String username) {
        String match = "MATCH (u : User) -[r : register]-> (a : Account)";
        String where = " WHERE u.email=" + "'" + username + "'";
        return match + where + " RETURN r";
    }

}
