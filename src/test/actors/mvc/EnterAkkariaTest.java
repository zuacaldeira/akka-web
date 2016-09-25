package actors.mvc;

import actors.business.AbstractActorTest;
import actors.messages.AkkaMessage;
import actors.messages.EnterAkkaria;
import actors.messages.LeaveAkkaria;
import akka.actor.ActorRef;
import akka.testkit.JavaTestKit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import actors.mvc.views.ActorView;
import actors.mvc.views.UserActorView;
import actors.mvc.views.WelcomeActorView;
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
                mvcActor.tell(new LeaveAkkaria(ui, AkkaMessage.CANCEL), getRef());
                expectNoMsg();
            }
        };
    }

    @Override
    public void testUnhandled() {

    }


    @DataProvider(name = "mvcActors")
    public Object[][] mvcActors() {
        return new Object[][] {
                {new WelcomeUI(), WelcomeActor.class, new WelcomeActorView()},
                {new UserUI(), UserActor.class, new UserActorView()}
        };
    }
}