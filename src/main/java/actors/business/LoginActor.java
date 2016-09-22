package actors.business;

import actors.exceptions.InvalidLoginException;
import actors.exceptions.MultipleRegistrationException;
import actors.exceptions.UnexpectedException;
import actors.exceptions.UnregisteredUserException;
import actors.messages.AkkaMessage;
import actors.messages.LoginMessage;
import graphs.Neo4jQueryFactory;
import graphs.entities.Login;
import graphs.entities.Registration;
import org.neo4j.ogm.session.Session;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zua on 28.08.16.
 */
public class LoginActor extends BusinessActor {

    @Override
    public void onReceive(Object message) {
        if( message instanceof LoginMessage) {
            if(valid((LoginMessage) message))
                login((LoginMessage) message);
            else
                getSender().tell(AkkaMessage.LOGIN_INVALID, getSelf());

        }
        else
            unhandled(message);

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
        Session session = getNeo4jSession();
        // Retrieve a registration relationship with this user
        Registration registration = getRegistration(session, message);
        // Save the user
        createUser(session, registration);
        // Notifies the user
        acknowledgeSender(AkkaMessage.LOGIN_SUCCESSFUL);
    }

    private void acknowledgeSender(AkkaMessage message) {
        getSender().tell(message, getSelf());
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
            getSender().tell(AkkaMessage.LOGIN_INVALID, getSelf());
            throw new UnregisteredUserException(username);
        }
        // Move over this first element, and if there is at least another
        if(list.size() > 1) {
            getSender().tell(AkkaMessage.LOGIN_INVALID, getSelf());
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
