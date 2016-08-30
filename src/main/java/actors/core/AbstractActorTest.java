package actors.core;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by zua on 30.08.16.
 */
public class AbstractActorTest {

    private ActorSystem actorSystem;

    @BeforeMethod
    public void setUp() {
        actorSystem = ActorSystem.create("TestActorSystem");
    }


    @AfterMethod
    public void tearDown() {
        actorSystem.shutdown();
    }


    public ActorSystem getActorSystem() {
        return actorSystem;
    }

    public ActorRef createActor(Class<?> aclass) {
        return actorSystem.actorOf(Props.create(aclass), aclass.getSimpleName());
    }
}
