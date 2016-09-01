package views.ui;

import views.actors.ActorView;
import views.factories.ActorsViewFactory;

/**
 * Created by zua on 28.08.16.
 */
public class WelcomeLayout extends TopLayout {

    @Override
    protected void initActors() {
        ActorView actorView = ActorsViewFactory.getWelcomeActorView();
        addComponent(actorView);
    }

}
