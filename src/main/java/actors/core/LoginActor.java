package actors.core;

import actors.entities.Login;
import actors.entities.Registration;
import actors.messages.AkkaMessages;
import actors.messages.LoginMessage;
import akka.actor.Props;
import graphs.Neo4jSessionFactory;
import org.neo4j.ogm.session.Session;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by zua on 28.08.16.
 */
public class LoginActor extends MVCUntypedActor {

    public static final akka.actor.Props PROPS = Props.create(LoginActor.class);
    public static final String NAME = LoginActor.class.getSimpleName();
    public static final List<String> MESSAGES = Arrays.asList(new String[]{AkkaMessages.LOGIN});

    @Override
    public void onReceive(Object message) throws Throwable {
        if( message instanceof LoginMessage) {
            login((LoginMessage) message);
        }
    }

    private void login(LoginMessage message) {
        // Create driver
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();

        String match = "match (u:User)-[r:register]->(a:Account) ";
        String where = "where u.email =" + message.getUsername();
        String cypher = " return r";

        Registration r = session.queryForObject(Registration.class, cypher, Collections.emptyMap());

        session.save(new Login(r.getUser(), r.getAccount()));
        log.info("Login of [user({})]", message.getUsername()) ;
    }

}
