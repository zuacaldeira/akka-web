package actors.mvc;

import actors.business.AbstractActorTest;
import actors.messages.ControlMessage;
import actors.messages.world.EnterAkkaria;
import actors.messages.world.LeaveAkkaria;
import akka.actor.ActorRef;
import akka.testkit.JavaTestKit;
import graphs.entities.nodes.User;
import org.mockito.Mockito;
import org.testng.annotations.Test;
import views.ui.AkkaUI;
import views.ui.WelcomeUI;

import static org.mockito.Mockito.doNothing;

/**
 * Created by zua on 02.10.16.
 */
public abstract class MVCActorTest extends AbstractActorTest {


    protected void testEnterAkkaria(ActorRef subject) {
        new JavaTestKit(getActorSystem()) {
            {
                AkkaUI ui = Mockito.mock(WelcomeUI.class);
                doNothing().when(ui).enter(Mockito.any(ActorRef.class), Mockito.eq(WelcomeActor.class));

                subject.tell(new EnterAkkaria(), getRef());
                expectNoMsg();
            }
        };

    }


    protected void testLeaveAkkaria(ActorRef subject, ControlMessage status) {
        new JavaTestKit(getActorSystem()){
            {
                WelcomeUI ui = Mockito.mock(WelcomeUI.class);
                doNothing().when(ui).enter(Mockito.any(ActorRef.class), Mockito.eq(WelcomeActor.class));

                subject.tell(new LeaveAkkaria(ui, status), getRef());
                //expectMsgEquals(status);
            }
        };
    }

    protected void testControlMessage(ActorRef subject, ControlMessage status) {
        new JavaTestKit(getActorSystem()){
            {
                WelcomeUI ui = Mockito.mock(WelcomeUI.class);
                doNothing().when(ui).enter(Mockito.any(ActorRef.class), Mockito.eq(WelcomeActor.class));

                subject.tell(status, getRef());
                expectNoMsg();
            }
        };
    }

    protected void testUnhandled(Class<? extends MVCActor> actorClass) {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef loginActor = createActor(actorClass, new WelcomeUI(), new User());
                loginActor.tell(ControlMessage.UNKNOWN, getRef());
                expectNoMsg();
            }
        };
    }



    @Test
    public abstract void testUnhandled();
    @Test
    public abstract void testEnterAkkaria();
    @Test
    public abstract void testLeaveAkkaria();




}
