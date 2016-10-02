package actors.business;

import actors.Neo4JDatabaseTest;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import graphs.Neo4jSessionFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.util.Collections;

import static akka.pattern.Patterns.ask;

/**
 * Created by zua on 30.08.16.
 */
public abstract class AbstractActorTest  extends Neo4JDatabaseTest {

    private ActorSystem actorSystem;

    @BeforeMethod
    public void setUp() {
        actorSystem = ActorSystem.create(ActorSystems.ACTOR_SYSTEM.getAlias());
        deleteDatabase();
    }


    @AfterMethod
    public void tearDown() {
        //deleteDatabase();
        actorSystem.shutdown();
    }

    private void deleteDatabase() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        Result result = session.query("MATCH (n) DETACH DELETE n", Collections.EMPTY_MAP);
        System.out.println(result.iterator());
    }


    public ActorSystem getActorSystem() {
        return actorSystem;
    }

    public ActorRef createActor(Class<?> aclass, String name) {
        return actorSystem.actorOf(Props.create(aclass), name);
    }

    public ActorRef createActor(Class<?> aclass) {
        return actorSystem.actorOf(Props.create(aclass), RandomStringUtils.randomAlphabetic(10));
    }

    public ActorRef createActor(Class<?> aClass, ActorRef supervisor) throws Exception {
        return (ActorRef) Await.result(ask(supervisor,
                Props.create(aClass), 5000), Duration.create("10 seconds"));
    }
}
