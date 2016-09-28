package actors;

import graphs.Neo4jSessionFactory;
import graphs.entities.Account;
import graphs.entities.Registration;
import graphs.entities.User;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Collections;

/**
 * Created by zua on 22.09.16.
 */
public abstract class Neo4JDatabaseTest extends AbstractTest {
    @BeforeMethod
    public void setUp() {
        deleteDatabase();
    }

    private void deleteDatabase() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        Result result = session.query("MATCH (n) DETACH DELETE n", Collections.EMPTY_MAP);
        System.out.println(result.iterator());
    }


    @AfterMethod
    public void tearDown() {
        //deleteDatabase();
    }

    public void seed(String username, String password) {
        User u = new User(username, password);
        Account a = new Account(username, password);
        Registration r = new Registration(u, a);
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.beginTransaction();
        session.save(r, 2);
        session.getTransaction().commit();
    }




}
