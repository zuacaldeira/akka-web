package actors.business;

import actors.mvc.UserActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.testkit.JavaTestKit;

/**
 * Created by zua on 22.09.16.
 */
public class UserActorTest extends AbstractActorTest {

    @Override
    public void testUnhandled() {
        new JavaTestKit(getActorSystem()){
            {
                ActorRef subject = getActorSystem().actorOf(Props.create(UserActor.class));
                subject.tell("hello", getRef());
                expectNoMsg();
            }
        };
    }
}