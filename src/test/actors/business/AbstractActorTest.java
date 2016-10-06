package actors.business;

import actors.Neo4JDatabaseTest;
import actors.mvc.WelcomeActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import graphs.Neo4jSessionFactory;
import graphs.entities.nodes.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import views.ui.AkkaMocks;
import views.ui.WelcomeUI;

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

    public ActorRef createWelcome(Class<WelcomeActor> aclass) {
        return actorSystem.actorOf(Props.create(aclass, AkkaMocks.getWelcomeMockUI(), new User()), aclass.getSimpleName() + RandomStringUtils.randomAlphabetic(8));
    }
    public ActorRef createActor(Class<?> aclass) {
        return actorSystem.actorOf(Props.create(aclass, AkkaMocks.getWelcomeMockUI(), new User()), aclass.getSimpleName() + RandomStringUtils.randomAlphabetic(8));
    }

    public ActorRef createActor(Class<?> aclass, User user) {
        return actorSystem.actorOf(Props.create(aclass, AkkaMocks.getWelcomeMockUI(), user), aclass.getSimpleName() + RandomStringUtils.randomAlphabetic(8));
    }

    public ActorRef createActor(Class<?> aclass, WelcomeUI ui) {
        return actorSystem.actorOf(Props.create(aclass, ui, new User()), aclass.getSimpleName() + RandomStringUtils.randomAlphabetic(8));
    }
    public ActorRef createActor(Class<?> aclass, WelcomeUI ui, User user) {
        return actorSystem.actorOf(Props.create(aclass, ui, user), aclass.getSimpleName() + RandomStringUtils.randomAlphabetic(8));
    }

    public ActorRef createActor(Class<?> aClass, ActorRef supervisor) throws Exception {
        return (ActorRef) Await.result(ask(supervisor,
                Props.create(aClass), 5000), Duration.create("10 seconds"));
    }
}
