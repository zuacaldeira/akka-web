package views.actors;

import actors.core.WelcomeActor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedList;

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
        ActorView av1 = new ActorView(WelcomeActor.class, new LinkedList<>()) {
            @Override
            protected void addContent() {}
        };
        ActorView av2 = new ActorView(WelcomeActor.class, new LinkedList<>()) {
            @Override
            protected void addContent() {}
        };
        return new Object[][] {
                {av1, av2}, {av1, av1}
        };
    }
}