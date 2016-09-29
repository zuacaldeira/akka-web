package views.ui;

import actors.mvc.*;
import actors.mvc.views.ActorView;
import actors.mvc.views.ActorsViewFactory;
import akka.actor.ActorRef;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNull;

/**
 * Created by zua on 28.09.16.
 */
public class StackedLayoutTest {
    @Test(dataProvider = "actorsAndViews")
    public void testGetActorView(ActorRef actor, ActorView actorView) throws Exception {
        StackedLayout layout = new StackedLayout();
        assertNull(layout.getCurrentActor());
        assertNull(layout.getCurrentView());
        // Add a view
        layout.push(actor, actorView);
        assertEquals(layout.getCurrentActor(), actor);
        assertEquals(layout.getCurrentView(), actorView);
    }

    @Test(dataProvider = "actorsAndViews")
    public void testRemoveActorView(ActorRef actor, ActorView actorView) throws Exception {
        StackedLayout layout = new StackedLayout();
        assertNull(layout.getCurrentActor());
        assertNull(layout.getCurrentView());
        // Add a view
        layout.push(actor, actorView);
        assertEquals(layout.getCurrentActor(), actor);
        assertEquals(layout.getCurrentView(), actorView);

        ActorView actorView2 = ActorsViewFactory.getInstance().getActorView(ProjectActor.class);
        ActorRef actor2 = AkkaUI.createActorRef(ProjectActor.class, "PA2");

        layout.push(actor2, actorView2);
        assertEquals(layout.getCurrentActor(), actor2);
        assertEquals(layout.getCurrentView(), actorView2);

        layout.pop();
        assertEquals(layout.getCurrentActor(), actor);
        assertEquals(layout.getCurrentView(), actorView);

    }

    @Test(dataProvider = "actorsAndViews")
    public void testAddActorView(ActorRef actor, ActorView actorView) throws Exception {
        StackedLayout layout = new StackedLayout();
        assertNull(layout.getCurrentActor());
        assertNull(layout.getCurrentView());
        // Add a view
        layout.push(actor, actorView);
        assertEquals(layout.getCurrentActor(), actor);
        assertEquals(layout.getCurrentView(), actorView);
    }



    @DataProvider(name = "actorsAndViews")
    public Object[][] actorsAndViews() {

        return new Object[][] {
                {AkkaUI.createActorRef(WelcomeActor.class, "WA"), ActorsViewFactory.getInstance().getActorView(WelcomeActor.class)},
                {AkkaUI.createActorRef(LoginActor.class, "WA"), ActorsViewFactory.getInstance().getActorView(LoginActor.class)},
                {AkkaUI.createActorRef(RegisterActor.class, "WA"), ActorsViewFactory.getInstance().getActorView(RegisterActor.class)},
                {AkkaUI.createActorRef(UserActor.class, "WA"), ActorsViewFactory.getInstance().getActorView(UserActor.class)},
                {AkkaUI.createActorRef(ProfileActor.class, "WA"), ActorsViewFactory.getInstance().getActorView(ProfileActor.class)},
                {AkkaUI.createActorRef(ProjectActor.class, "WA"), ActorsViewFactory.getInstance().getActorView(ProjectActor.class)},
        };
    }

}