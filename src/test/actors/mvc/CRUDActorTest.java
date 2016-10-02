package actors.mvc;

import actors.business.AbstractActorTest;
import actors.messages.ControlMessage;
import actors.messages.crud.CreateMessage;
import actors.messages.crud.CrudMessage;
import actors.messages.crud.UpdateMessage;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import graphs.entities.Account;
import graphs.entities.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by zua on 27.09.16.
 */
public class CRUDActorTest extends AbstractActorTest {

    @DataProvider(name = "crudActors")
    public Object[][] crudActors() {
        return new Object[][] {
                {new CreateMessage<User>(new User())},
                {new CreateMessage<Account>(new Account())},
                {new UpdateMessage<User>(new User("uername", "Full Name 1"))},
                {new UpdateMessage<Account>(new Account("u", "p1"))}
        };
    }

    @Test(dataProvider = "crudActors")
    public void testCreate(CrudMessage<?> message) {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef crudActor = getActorSystem().actorOf(Props.create(ProjectActor.class), "PA");
                crudActor.tell(message, getRef());
                expectMsgAnyOf(ControlMessage.SUCCESSFUL);
            }
        };
    }

}