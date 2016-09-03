package views.actors;

import actors.core.WelcomeActor;
import actors.messages.AkkaMessages;
import com.vaadin.ui.Button;
import views.factories.ActorsViewFactory;

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

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton().getCaption().equals(AkkaMessages.REGISTER)) {
            getUI().setContent(ActorsViewFactory.getInstance().getRegisterActorView());
        }
        if (event.getButton().getCaption().equals(AkkaMessages.LOGIN)) {
            getUI().setContent(ActorsViewFactory.getInstance().getLoginActorView());
        }
    }
}
