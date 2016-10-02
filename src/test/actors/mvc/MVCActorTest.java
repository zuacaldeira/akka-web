package actors.mvc;

import actors.business.AbstractActorTest;
import actors.messages.ControlMessage;
import actors.messages.world.EnterAkkaria;
import actors.messages.world.LeaveAkkaria;
import actors.mvc.views.ActorView;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import org.mockito.Mockito;
import org.testng.annotations.Test;
import views.ui.AkkaUI;
import views.ui.UserUI;

import static org.mockito.Mockito.doNothing;

/**
 * Created by zua on 02.10.16.
 */
public abstract class MVCActorTest extends AbstractActorTest {


    protected void testEnterAkkaria(Class<? extends AkkaUI> uiClass, Class<? extends MVCActor> aClass) {
        new JavaTestKit(getActorSystem()) {
            {
                AkkaUI ui = Mockito.mock(uiClass);
                doNothing().when(ui).enter(Mockito.any(ActorRef.class), Mockito.any(ActorView.class));

                ActorRef subject = getActorSystem().actorOf(Props.create(aClass));
                subject.tell(new EnterAkkaria(ui), getRef());
                expectNoMsg();
            }
        };

    }


    protected void testLeaveAkkaria(Class<? extends AkkaUI> uiClass, Class<? extends MVCActor> aClass, ControlMessage status) {
        new JavaTestKit(getActorSystem()){
            {
                UserUI ui = Mockito.mock(UserUI.class);
                doNothing().when(ui).enter(Mockito.any(ActorRef.class), Mockito.any(ActorView.class));

                ActorRef subject = getActorSystem().actorOf(Props.create(UserActor.class));
                subject.tell(new LeaveAkkaria(ui, status), getRef());
                expectMsgEquals(status);
            }
        };
    }

    protected void testControlMessage(Class<? extends AkkaUI> uiClass, Class<? extends MVCActor> aClass, ControlMessage status) {
        new JavaTestKit(getActorSystem()){
            {
                UserUI ui = Mockito.mock(UserUI.class);
                doNothing().when(ui).enter(Mockito.any(ActorRef.class), Mockito.any(ActorView.class));

                ActorRef subject = getActorSystem().actorOf(Props.create(UserActor.class));
                subject.tell(status, getRef());
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
