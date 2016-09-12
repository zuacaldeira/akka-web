package views.components;

import org.testng.annotations.Test;
import views.actors.WelcomeActorView;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 31.08.16.
 */
public class WelcomeLayoutTest {
    @Test
    public void testInitActors() throws Exception {
        WelcomeLayout layout = new WelcomeLayout();
        assertEquals(layout.getComponentCount(), 2); // Mailboxes and actorLabelRef
        assertTrue(layout.getComponent(0) instanceof WelcomeActorView);
    }



}