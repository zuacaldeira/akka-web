package views.actors;

import actors.core.RegisterActor;
import actors.messages.AkkaMessages;
import views.ui.RegisterForm;

/**
 * Created by zua on 02.09.16.
 */
public class RegisterActorView extends ActorView {
    /**
     * Register actor view.
     */
    public RegisterActorView() {
        super(RegisterActor.class, AkkaMessages.getRegisterActorMessages());    }

    @Override
    protected void addContent() {
        addComponent(new RegisterForm(getActorRef()));
    }
}
