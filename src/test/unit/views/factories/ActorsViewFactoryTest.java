package views.factories;

import org.testng.annotations.Test;
import views.actors.WelcomeActorView;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 01.09.16.
 */
public class ActorsViewFactoryTest {
    @Test
    public void testGetWelcomeActorView() throws Exception {
        WelcomeActorView view = (WelcomeActorView) ActorsViewFactory.getInstance().getWelcomeActorView();
        assertNotNull(view.getActorRef());
        assertNotNull(view.getMailboxes());

    }
}