package views.actors;

import actors.core.LoginActor;
import actors.messages.AkkaMessages;

/**
 * Created by zua on 02.09.16.
 */
public class LoginActorView extends ActorView {

    /**
     * Login actor view.
     */
    public LoginActorView() {
        super(LoginActor.class, AkkaMessages.getLoginActorMessages());
    }

    @Override
    protected void addContent() {
        addComponent(new views.ui.LoginForm(getActorRef()));
    }
}
