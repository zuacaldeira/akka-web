package actors.core;

import actors.core.exceptions.IllegalRegistrationException;
import actors.messages.AkkaMessages;
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
public class RegisterActor extends MVCUntypedActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        if( message instanceof RegisterMessage) {
            register((RegisterMessage) message);
        }
        else {
            unhandled(message);
        }
    }

    private void register(RegisterMessage message) throws IllegalRegistrationException {
        try {
            //  Create session
            Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
            // query the graph for a matching user
            User u = session.queryForObject(User.class, Neo4jQueryFactory.getInstance().findUserByEmailQuery(message.getEmail()), Collections.EMPTY_MAP);
            // If we found one, then this registration is invalid because it will lead to duplicated regustrations
            if(u != null) {
                throw new IllegalRegistrationException("User already exists: " + message.getEmail());
            }
            // Otherwise, store a new registration in the graph
            session.save(new Registration(new User(message.getEmail(), message.getFullname()), new Account(message.getEmail(), message.getPassword())));
            // Return a confirmation message to the caller
            getSender().tell(AkkaMessages.DONE, getSelf());
            log.info("Stored registration for user [{}]", message.getFullname());
        } catch(Exception e) {
            // An error occurred, inform sender and exits
            log.error("Error during storage of registration for user {}", message.getFullname());
            getSender().tell(AkkaMessages.ERROR + ": " + e.getMessage(), getSelf());
            throw e;
        }
    }

}
