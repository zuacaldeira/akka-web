package actors.core;

import actors.core.exceptions.IllegalRegistrationException;
import actors.messages.AkkaMessages;
import actors.messages.RegisterMessage;
import graphs.Neo4jSessionFactory;
import graphs.entities.Account;
import graphs.entities.Registration;
import graphs.entities.User;
import org.neo4j.ogm.session.Session;

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
        // Store a new exchange in Neo4J
        // Create driver
        try {
            Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
            session.save(
                    new Registration(
                            new User(message.getEmail(), message.getFullname()),
                            new Account(message.getEmail(), message.getPassword())));
            // Return a confirmation message to the caller
            getSender().tell(AkkaMessages.DONE, getSelf());
            // Read a Node
            log.info("Stored registration for user [{}]", message.getFullname());
        } catch(IllegalRegistrationException e) {
            log.error("Error during storage of registration for user {}", message.getFullname());
            getSender().tell(e, getSelf());
            throw e;
        } catch(Exception e) {
            log.error("Error during storage of registration for user {}", message.getFullname());
            IllegalRegistrationException ex = new IllegalRegistrationException(e.getMessage());
            getSender().tell(ex, getSelf());
            throw e;
        }
    }

}
