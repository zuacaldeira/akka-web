package actors;

import graphs.Neo4jSessionFactory;
import graphs.entities.nodes.Account;
import graphs.entities.nodes.RegisteredAs;
import graphs.entities.nodes.User;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import javax.transaction.Transactional;
import java.util.Collections;

/**
 * Created by zua on 22.09.16.
 */
@Transactional
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

    public void seed(String username, String password, String fullname) {
        User u = new User(username, fullname);
        Account a = new Account(username, password);
        RegisteredAs r = new RegisteredAs(u, a);
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.beginTransaction();
        session.save(r, 2);
        session.getTransaction().commit();
    }

    public void seed(String username, String password) {
        seed(username, password, "Seeded Fullname");
    }



}
