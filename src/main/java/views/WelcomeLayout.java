package views;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import actors.core.WelcomeActor;
import views.factories.ActorView;
import views.factories.ActorsViewFactory;

/**
 * Created by zua on 28.08.16.
 */
public class WelcomeLayout extends TopLayout {

    private ActorRef welcomeActor;

    public WelcomeLayout() {
    }

    @Override
    protected void initActors() {
        welcomeActor = ActorSystem.create().actorOf(WelcomeActor.PROPS, WelcomeActor.NAME);
        ActorView actorView = ActorsViewFactory.getActorView(welcomeActor, WelcomeActor.MESSAGES);
        addComponent(actorView);
    }

}
