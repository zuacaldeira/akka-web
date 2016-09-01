package views.actors;

import actors.core.WelcomeActor;
import actors.messages.AkkaMessages;

/**
 * Created by zua on 28.08.16.
 */
public class WelcomeActorView extends ActorView {
    public WelcomeActorView() {
        super(WelcomeActor.class, AkkaMessages.getWelcomeActorMessages());
    }
}
