package actors.core;

import actors.entities.Account;
import actors.entities.Registration;
import actors.entities.User;
import actors.messages.AkkaMessages;
import actors.messages.RegisterMessage;
import akka.actor.Props;
import graphs.Neo4jSessionFactory;
import org.neo4j.ogm.session.Session;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zua on 28.08.16.
 */
public class RegisterActor extends MVCUntypedActor {
    public static final String NAME = RegisterActor.class.getSimpleName();
    public static final Props PROPS = Props.create(RegisterActor.class);
    public static final List<String> MESSAGES = Arrays.asList(new String[]{AkkaMessages.REGISTER});

    @Override
    public void onReceive(Object message) throws Throwable {
        if( message instanceof RegisterMessage) {
            register((RegisterMessage) message);
            getSender().tell(AkkaMessages.DONE, getSelf());
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
        log.info("Registered [user({})]", session.countEntitiesOfType(User.class));
    }

}
