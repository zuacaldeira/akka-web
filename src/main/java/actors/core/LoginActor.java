package actors.core;

import actors.core.exceptions.*;
import actors.messages.AkkaMessages;
import actors.messages.LoginMessage;
import graphs.Neo4jQueryFactory;
import graphs.Neo4jSessionFactory;
import graphs.entities.Login;
import graphs.entities.Registration;
import org.neo4j.ogm.session.Session;
import views.actors.LoginActorView;

import java.util.Collections;

/**
 * Created by zua on 28.08.16.
 */
public class LoginActor extends MVCUntypedActor {

    private LoginActorView view;

    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof LoginActorView) {
            this.view = (LoginActorView) message;
        }
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
        view.getUI().getPage().setLocation("/user");
    }

    private void acknowledgeSender(String status) {
        getSender().tell(status, getSelf());
    }

    private void createUser(Session session, Registration registration) {
        session.save(new Login(registration.getUser(), registration.getAccount()));
    }

    private Registration getRegistration(Session session, LoginMessage message) {
        // Tries to find a unique registration for this user
        IllegalLoginException ex = null;
        try {
            Registration registration =  session.queryForObject(Registration.class, Neo4jQueryFactory.getInstance().findRegisterByEmailQuery(message.getUsername()), Collections.emptyMap());
            if(registration != null) {
                return registration;
            } else {
                ex = new UnregisteredUserException(message.getUsername());
                getSender().tell(ex, getSelf());
                throw ex;
            }
        } catch (RuntimeException rex) { // duplications detected
            ex = new MultipleRegistrationException(message.getUsername());
            getSender().tell(ex, getSelf());
            throw ex;
        }
    }


    private Session getNeo4jSession() {
        try {
            return Neo4jSessionFactory.getInstance().getNeo4jSession();
        } catch (Exception e) {
            throw new UnexpectedException("Problems creating a Neo4j Session", e);
        }
    }
}
