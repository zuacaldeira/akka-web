package actors.mvc;

import actors.business.LoginValidator;
import actors.exceptions.InvalidLoginException;
import actors.exceptions.MultipleRegistrationException;
import actors.exceptions.UnexpectedException;
import actors.exceptions.UnregisteredUserException;
import actors.messages.ControlMessage;
import actors.messages.LoginMessage;
import graphs.Neo4jQueryFactory;
import graphs.Neo4jSessionFactory;
import graphs.entities.Login;
import graphs.entities.Registration;
import org.neo4j.ogm.session.Session;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zua on 28.08.16.
 */
public class LoginActor extends MVCActor{

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

    @Override
    protected void leaveAkkariaOnSuccess() {
        if(getUi() != null) {
            getUi().jump("/user");
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
        // Retrieve a registration relationship with this user
        Registration registration = getRegistration(session, message);
        // Save the user
        createUser(session, registration);
        // Notifies the user
        leaveAkkariaOnSuccess();
    }

    private void acknowledgeSender(ControlMessage message) {
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

    private Iterable<Registration> findAllRegistrationsByEmail(String username) {
        return Neo4jSessionFactory.getInstance().getNeo4jSession().query(
            Registration.class,
            Neo4jQueryFactory.getInstance().findRegisterByEmailQuery(username),
            new HashMap<>()
        );
    }

}
