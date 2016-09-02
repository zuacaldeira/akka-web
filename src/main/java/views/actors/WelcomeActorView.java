package views.actors;

import actors.core.WelcomeActor;
import actors.messages.AkkaMessages;

/**
 * Created by zua on 28.08.16.
 */
public class WelcomeActorView extends ActorView {

    /**
     * Creates a view for the {@link WelcomeActor}.
     *
     * @see {@link WelcomeActor}, {@link ActorView}.
     */
    public WelcomeActorView() {
        super(WelcomeActor.class, AkkaMessages.getWelcomeActorMessages());
    }

    @Override
    protected void addContent() {

    }
}
