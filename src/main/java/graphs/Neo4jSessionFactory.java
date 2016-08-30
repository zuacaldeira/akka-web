package graphs;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 * Created by zua on 30.08.16.
 */
public class Neo4jSessionFactory {
    private static Neo4jSessionFactory singleton = new Neo4jSessionFactory();
    private final SessionFactory sessionFactory ;

    public static Neo4jSessionFactory getInstance() {
        return singleton;
    }

    private Neo4jSessionFactory() {
        sessionFactory = new SessionFactory(getConfiguration(), "");
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.driverConfiguration()
                .setCredentials("neo4j", "unicidade")
                .setURI("http://localhost:7474");
        return configuration;
    }

    public Session getNeo4jSession() {
        return sessionFactory.openSession();
    }
}
