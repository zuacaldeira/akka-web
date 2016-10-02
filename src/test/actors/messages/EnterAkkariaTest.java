package actors.messages;

import actors.business.AbstractActorTest;
import actors.messages.world.EnterAkkaria;
import actors.messages.world.LeaveAkkaria;
import actors.mvc.MVCActor;
import actors.mvc.UserActor;
import actors.mvc.WelcomeActor;
import actors.mvc.views.ActorView;
import actors.mvc.views.UserActorView;
import actors.mvc.views.WelcomeActorView;
import akka.actor.ActorRef;
import akka.testkit.JavaTestKit;
import org.mockito.Mockito;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import views.ui.AkkaUI;
import views.ui.UserUI;
import views.ui.WelcomeUI;

import static org.testng.Assert.assertNull;

/**
 * Created by zua on 23.09.16.
 */
public class EnterAkkariaTest extends AbstractActorTest {


    @Test(dataProvider = "mvcActors")
    public void testEnterMVCActors(AkkaUI ui, Class<? extends MVCActor> aClass, ActorView view) {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef mvcActor = createActor(aClass);

                assertNull(ui.getContent());


                mvcActor.tell(new EnterAkkaria(ui), getRef());
                expectNoMsg();
                mvcActor.tell(new LeaveAkkaria(ui, ControlMessage.CANCELLED), getRef());
                expectMsgEquals(ControlMessage.CANCELLED);
            }
        };
    }


    @DataProvider(name = "mvcActors")
    public Object[][] mvcActors() {
        return new Object[][] {
                {getMockedWelcomeUI(), WelcomeActor.class, new WelcomeActorView()},
                {getMockedUserUI(), UserActor.class, new UserActorView()}
        };
    }

    private static WelcomeUI getMockedWelcomeUI() {
        WelcomeUI ui = Mockito.mock(WelcomeUI.class);
        Mockito.doNothing().when(ui).enter(Mockito.any(ActorRef.class), Mockito.any(ActorView.class));
        return ui;
    }


    private static UserUI getMockedUserUI() {
        UserUI ui = Mockito.mock(UserUI.class);
        Mockito.doNothing().when(ui).enter(Mockito.any(ActorRef.class), Mockito.any(ActorView.class));
        return ui;
    }
}