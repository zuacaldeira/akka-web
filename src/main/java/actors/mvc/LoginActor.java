package actors.mvc;

import actors.business.LoginValidator;
import actors.exceptions.InvalidLoginException;
import actors.exceptions.MultipleRegistrationException;
import actors.exceptions.UnexpectedException;
import actors.exceptions.UnregisteredUserException;
import actors.messages.LoginMessage;
import akka.actor.ActorRef;
import graphs.Neo4jQueryFactory;
import graphs.Neo4jSessionFactory;
import graphs.entities.nodes.LoginAs;
import graphs.entities.nodes.RegisteredAs;
import graphs.entities.nodes.User;
import org.neo4j.ogm.session.Session;
import views.ui.AkkaUI;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zua on 28.08.16.
 */
public class LoginActor extends MVCActor{

    protected LoginActor(AkkaUI ui, User user) {
        super(ui, user);
    }

    @Override
    public void onReceive(Object message) {
        if( message instanceof LoginMessage) {
            if(valid((LoginMessage) message))
                login((LoginMessage) message);
        }
        else {
            super.onReceive(message);
        }

    }

    private boolean valid(LoginMessage message) {
        return new LoginValidator().isValid(message);
    }

    /**
     * Store a login relationship between a user an her's account.
     *
     * @param message A message with user's credentials
     * @throws UnexpectedException If in presence of a non business exception (like
     * exceptions connecting to the database).
     * @throws InvalidLoginException If can't find a registration for the user
     */
    private void login(LoginMessage message) {
        // Create session
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        // Retrieve a registeredAs relationship with this user
        RegisteredAs registeredAs = getRegistration(session, message);
        // Save the user
        createUser(session, registeredAs);
        // Notifies the user
        leaveAkkariaOnSuccess(registeredAs.getUser());
    }

    private void createUser(Session session, RegisteredAs registeredAs) {
        session.save(new LoginAs(registeredAs.getUser(), registeredAs.getAccount()));
    }

    private RegisteredAs getRegistration(Session session, LoginMessage message) {
        // Tries to find a unique registration for this user
        Iterable<RegisteredAs> registrations = findAllRegistrationsByEmail(session, message.getUsername());
        return getTheOneAndOnly(registrations, message.getUsername());
    }

    private RegisteredAs getTheOneAndOnly(Iterable<RegisteredAs> registrations, String username) {
        List<RegisteredAs> list = new LinkedList<>();
        registrations.forEach(list::add);
        // If there is one and one registration entity only, then return it
        if(list.size() == 1) {
            return list.get(0);
        }
        // Handle business violation
        if(list.isEmpty()) {
            leaveAkkariaUIOnBusinessViolation(new UnregisteredUserException(username));
        }
        else if(list.size() > 1) {
            leaveAkkariaUIOnBusinessViolation(new MultipleRegistrationException(username));
        }
        return null;
    }

    private Iterable<RegisteredAs> findAllRegistrationsByEmail(Session session, String username) {
        return session.query(
            RegisteredAs.class,
            Neo4jQueryFactory.getInstance().findRegisterByEmailQuery(username),
            new HashMap<>()
        );
    }

    @Override
    protected ActorRef getNextActor(Object... args) {
        return createChildActor(UserActor.class, getUi(), (User) args[0]);
    }
}
