package views;

import actors.core.WelcomeActor;
import actors.messages.AkkaMessages;
import views.factories.ActorView;
import views.factories.ActorsViewFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zua on 28.08.16.
 */
public class WelcomeLayout extends TopLayout {


    public WelcomeLayout() {
    }

    @Override
    protected void initActors() {
        List<String> messages = Arrays.asList(AkkaMessages.REGISTER, AkkaMessages.LOGIN);
        ActorView actorView = ActorsViewFactory.getActorView(WelcomeActor.class, messages);
        addComponent(actorView);
    }

}
