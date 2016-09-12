package actors.core;

import actors.core.exceptions.IllegalLoginException;
import actors.core.exceptions.MultipleRegistrationException;
import actors.core.exceptions.UnexpectedException;
import actors.core.exceptions.UnregisteredUserException;
import actors.messages.AkkaMessages;
import actors.messages.LoginMessage;
import graphs.Neo4jQueryFactory;
import graphs.entities.Login;
import graphs.entities.Registration;
import org.neo4j.ogm.session.Session;
import views.actors.LoginActorView;
import views.factories.ActorsViewFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zua on 28.08.16.
 */
public class LoginActor extends MVCUntypedActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof LoginActorView) {
            super.setView((LoginActorView) message);
            log.info("Recieved a view...");
        }
        else if( message instanceof LoginMessage) {
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
        try {
            // Create session
            Session session = getNeo4jSession();
            // Retrieve a registration relationship with this user
            Registration registration = getRegistration(session, message);
            // Save the user
            createUser(session, registration);
            // Notifies the user
            acknowledgeSender(AkkaMessages.DONE);
            if(super.getView() != null) {
                log.info("View == null? {}", getView() == null);
                getView().getUI().getPage().setLocation("/user");
            }
        } catch (RuntimeException e) {
            log.info("Login failed ");
            acknowledgeSender("Login failed: " + e.getMessage());
            if(getView() != null) {
                log.info("View == null? {}", getView() == null);
                getView().getUI().setContent(ActorsViewFactory.getInstance().getWelcomeActorView());
            }
            throw e;
        }
    }

    private void acknowledgeSender(String status) {
        getSender().tell(status, getSelf());
    }

    private void createUser(Session session, Registration registration) {
        session.save(new Login(registration.getUser(), registration.getAccount()));
    }

    private Registration getRegistration(Session session, LoginMessage message) {
        // Tries to find a unique registration for this user
        Iterable<Registration> registrations = findAllRegistrationsByEmail(message.getUsername());
        return getTheOneAndOnly(registrations, message.getUsername());
    }

    private Registration getTheOneAndOnly(Iterable<Registration> registrations, String username) {
        List<Registration> list = new LinkedList<>();
        registrations.forEach(r -> {
            list.add(r);
        });
        // There is one
        if(list.isEmpty()) {
            throw new UnregisteredUserException(username);
        }
        // Move over this first element, and if there is at least another
        if(list.size() > 1) {
            throw new MultipleRegistrationException(username);
        }
        // This is the one and only one
        return list.get(0);
    }

    private Iterable<Registration> findAllRegistrationsByEmail(String username) {
        return getNeo4jSession().query(
            Registration.class,
            Neo4jQueryFactory.getInstance().findRegisterByEmailQuery(username),
            new HashMap<>()
        );
    }

}
