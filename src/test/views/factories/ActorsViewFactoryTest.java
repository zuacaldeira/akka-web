package views.factories;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import views.actors.ActorView;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 01.09.16.
 */
public class ActorsViewFactoryTest {
    @Test(dataProvider = "views")
    public void testFactoryMethods(ActorView view) throws Exception {
        assertNotNull(view.getActorRef());
        assertNotNull(view.getMailboxes());
    }

    @DataProvider(name = "views")
    public Object[][] views() {
        return new Object[][] {
                {ActorsViewFactory.getInstance().getWelcomeActorView()},
                {ActorsViewFactory.getInstance().getRegisterActorView()},
                {ActorsViewFactory.getInstance().getLoginActorView()}
        };
    }

}