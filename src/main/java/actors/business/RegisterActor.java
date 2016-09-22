package actors.business;

import actors.exceptions.InvalidRegistrationException;
import actors.messages.AkkaMessage;
import actors.messages.RegisterMessage;
import graphs.Neo4jQueryFactory;
import graphs.Neo4jSessionFactory;
import graphs.entities.Account;
import graphs.entities.Registration;
import graphs.entities.User;
import org.neo4j.ogm.session.Session;

import java.util.Collections;

/**
 * Created by zua on 28.08.16.
 */
public class RegisterActor extends BusinessActor {

    @Override
    public void onReceive(Object message) {
        if( message instanceof RegisterMessage) {
            if(valid((RegisterMessage) message))
                register((RegisterMessage) message);
            else
                getSender().tell(AkkaMessage.REGISTER_INVALID, getSelf());
        }
        else {
            unhandled(message);
        }
    }

    private boolean valid(RegisterMessage message) {
        return new RegistrationValidator().isValid(message);
    }

    private void register(RegisterMessage message) throws InvalidRegistrationException {
        //  Create session
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        // query the graph for a matching user
        User u = session.queryForObject(User.class, Neo4jQueryFactory.getInstance().findUserByEmailQuery(message.getEmail()), Collections.EMPTY_MAP);
        // If we found one, then this registration is invalid because it will lead to duplicated registrations
        if(u != null) {
            throw new InvalidRegistrationException("User already exists: " + message.getEmail());
        }
        // Otherwise, store a new registration in the graph
        session.save(new Registration(new User(message.getEmail(), message.getFullname()), new Account(message.getEmail(), message.getPassword())));
        // Return a confirmation message to the caller
        getSender().tell(AkkaMessage.REGISTER_SUCCESSFUL, getSelf());
        log.info("Stored registration for user [{}]", message.getFullname());
    }

}
