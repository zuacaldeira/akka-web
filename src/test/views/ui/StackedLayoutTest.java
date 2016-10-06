package views.ui;

import actors.mvc.*;
import actors.mvc.views.ActorView;
import actors.mvc.views.ActorsViewFactory;
import actors.mvc.views.WelcomeActorView;
import akka.actor.ActorRef;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by zua on 28.09.16.
 */
public class StackedLayoutTest {
    @Test(dataProvider = "actorsAndViews")
    public void testGetActorView(ActorRef actor, ActorView actorView, Class<? extends MVCActor> aClass) throws Exception {
        StackedLayout layout = new StackedLayout();
        assertNull(layout.getCurrentActor());
        assertNull(layout.getCurrentView());
        // Add a view
        layout.enter(actor,  aClass);
        assertNotNull(layout.getCurrentActor());
        assertNotNull(layout.getCurrentView());

        assertEquals(layout.getCurrentActor(), actor);
        assertEquals(layout.getCurrentView().getClass(), actorView.getClass());
    }

    @Test(dataProvider = "actorsAndViews")
    public void testRemoveActorView(ActorRef actor, ActorView actorView, Class<? extends MVCActor> aClass) throws Exception {
        StackedLayout layout = new StackedLayout();
        assertNull(layout.getCurrentActor());
        assertNull(layout.getCurrentView());
        // Add a view
        layout.enter(actor, aClass);
        assertNotNull(layout.getCurrentActor());
        assertNotNull(layout.getCurrentView());

        assertEquals(layout.getCurrentActor(), actor);
        assertEquals(layout.getCurrentView().getClass(), actorView.getClass());

        ActorRef actor2 = new WelcomeUI().createActorRef(WelcomeActor.class);

        layout.enter(actor2, WelcomeActor.class);
        assertEquals(layout.getCurrentActor(), actor2);
        assertEquals(layout.getCurrentView().getClass(), WelcomeActorView.class);

        layout.leave(actor2);
        assertEquals(layout.getCurrentActor(), actor);
        assertEquals(layout.getCurrentView().getClass(), actorView.getClass());
    }

    @Test(dataProvider = "actorsAndViews")
    public void testAddActorView(ActorRef actor, ActorView actorView, Class<? extends MVCActor> aClass) throws Exception {
        StackedLayout layout = new StackedLayout();
        assertNull(layout.getCurrentActor());
        assertNull(layout.getCurrentView());
        // Add a view
        layout.enter(actor, aClass);
        assertNotNull(layout.getCurrentActor());
        assertNotNull(layout.getCurrentView());
        assertEquals(layout.getCurrentActor(), actor);
        assertEquals(layout.getCurrentView().getClass(), actorView.getClass());
    }



    @DataProvider(name = "actorsAndViews")
    public Object[][] actorsAndViews() {
        Class[] classes = new Class[]{WelcomeActor.class, RegisterActor.class, LoginActor.class, UserActor.class, ProfileActor.class, ProjectActor.class};
        return new Object[][] {
                {new WelcomeUI().createActorRef(classes[0]), ActorsViewFactory.getInstance().getActorView(classes[0]), classes[0]},
                {new WelcomeUI().createActorRef(classes[1]), ActorsViewFactory.getInstance().getActorView(classes[1]), classes[1]},
                {new WelcomeUI().createActorRef(classes[2]), ActorsViewFactory.getInstance().getActorView(classes[2]), classes[2]},
                {new WelcomeUI().createActorRef(classes[3]), ActorsViewFactory.getInstance().getActorView(classes[3]), classes[3]},
                {new WelcomeUI().createActorRef(classes[4]), ActorsViewFactory.getInstance().getActorView(classes[4]), classes[4]},
                {new WelcomeUI().createActorRef(classes[5]), ActorsViewFactory.getInstance().getActorView(classes[5]), classes[5]},
        };
    }

}