package actors.business;

import graphs.Neo4jSessionFactory;
import org.neo4j.ogm.session.Session;

/**
 * Created by zua on 21.09.16.
 */
public abstract class BusinessActor extends AkkaActor {
    protected Session getNeo4jSession() {
        return Neo4jSessionFactory.getInstance().getNeo4jSession();
    }
}
