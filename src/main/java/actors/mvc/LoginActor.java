package actors.mvc;

import actors.business.LoginValidator;
import actors.exceptions.InvalidLoginException;
import actors.exceptions.UnexpectedException;
import actors.messages.AkkaMessage;
import actors.messages.EnterAkkaria;
import actors.messages.LoginMessage;
import graphs.Neo4jQueryFactory;
import graphs.Neo4jSessionFactory;
import graphs.entities.Login;
import graphs.entities.Registration;
import org.neo4j.ogm.session.Session;
import actors.mvc.views.LoginActorView;

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
    protected void enterUI(EnterAkkaria message) {
        if(message.getUi() != null) {
            message.getUi().enter(getSelf(), new LoginActorView());
        }
    }

    @Override
    protected void leaveAkkariaOnSuccess() {
        getUi().jump("/user");
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
        if(list.isEmpty() || list.size() > 1) {
            leaveAkkariaOnFailure();
        }
        // This is the one and only one
        return list.get(0);
    }

    private Iterable<Registration> findAllRegistrationsByEmail(String username) {
        return Neo4jSessionFactory.getInstance().getNeo4jSession().query(
            Registration.class,
            Neo4jQueryFactory.getInstance().findRegisterByEmailQuery(username),
            new HashMap<>()
        );
    }

}
