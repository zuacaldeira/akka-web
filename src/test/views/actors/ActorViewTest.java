package views.actors;

import actors.core.WelcomeActor;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 02.09.16.
 */
public class ActorViewTest {
    @Test(dataProvider = "equals")
    public void testEquals(ActorView a, ActorView b) throws Exception {
        assertTrue(a.equals(b));
    }

    @Test(dataProvider = "equals")
    public void testHashCode(ActorView a, ActorView b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @DataProvider(name = "equals")
    public Object[][] equals() {
        ActorView av1 = new ActorView(WelcomeActor.class) {
            @Override
            protected Component createActorContent() {
                return null;
            }

            @Override
            public void buttonClick(Button.ClickEvent event) {

            }
        };
        ActorView av2 = new ActorView(WelcomeActor.class) {
            @Override
            protected Component createActorContent() {
                return null;
            }

            @Override
            public void buttonClick(Button.ClickEvent event) {

            }
        };
        return new Object[][] {
                {av1, av2}, {av1, av1}
        };
    }
}