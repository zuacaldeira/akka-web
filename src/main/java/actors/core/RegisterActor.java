package actors.core;

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
            getSender().tell(AkkaMessages.DONE, getSelf());
        }
        else {
            unhandled(message);
        }
    }

    private void register(RegisterMessage message) {
        // Store a new exchange in Neo4J
        // Create driver
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();

        session.save(
                new Registration(
                        new User(message.getEmail(), message.getFullname()),
                        new Account(message.getEmail(), message.getPassword())));

        // Read a Node
        log.info("Stored registration for user [{}]", message.getFullname());
    }

}
