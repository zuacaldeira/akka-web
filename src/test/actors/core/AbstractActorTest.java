package actors.core;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import graphs.Neo4jSessionFactory;
import org.neo4j.ogm.session.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.util.Collections;

import static akka.pattern.Patterns.ask;

/**
 * Created by zua on 30.08.16.
 */
public abstract class AbstractActorTest {

    private ActorSystem actorSystem;

    @BeforeMethod
    public void setUp() {
        actorSystem = ActorSystem.create(ActorSystems.ACTOR_SYSTEM.getAlias());
        deleteDatabase();
    }


    @AfterMethod
    public void tearDown() {
        actorSystem.shutdown();
    }

    private void deleteDatabase() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        if(session != null) {
            session.query("MATCH (n) DETACH DELETE n", Collections.EMPTY_MAP);
        }
    }


    public ActorSystem getActorSystem() {
        return actorSystem;
    }

    public ActorRef createActor(Class<?> aclass) {
        return actorSystem.actorOf(Props.create(aclass), aclass.getSimpleName());
    }

    public ActorRef createActor(Class<?> aClass, ActorRef supervisor) throws Exception {
        return (ActorRef) Await.result(ask(supervisor,
                Props.create(aClass), 5000), Duration.create("10 seconds"));
    }



    @Test
    public abstract void testUnhandled();
}
