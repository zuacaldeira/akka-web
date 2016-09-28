package views.factories;

import actors.mvc.*;
import actors.mvc.views.ActorView;
import actors.mvc.views.ActorsViewFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 01.09.16.
 */
public class ActorsViewFactoryTest {
    @Test(dataProvider = "views")
    public void testFactoryMethods(ActorView view) throws Exception {
        assertNotNull(view.getActorContent());
        assertNotNull(view.getMailboxes());
    }

    @DataProvider(name = "views")
    public Object[][] views() {
        return new Object[][] {
                {ActorsViewFactory.getInstance().getActorView(WelcomeActor.class)},
                {ActorsViewFactory.getInstance().getActorView(RegisterActor.class)},
                {ActorsViewFactory.getInstance().getActorView(LoginActor.class)},
                {ActorsViewFactory.getInstance().getActorView(UserActor.class)},
                {ActorsViewFactory.getInstance().getActorView(ProfileActor.class)},
                {ActorsViewFactory.getInstance().getActorView(LoginActor.class)}
        };
    }

}