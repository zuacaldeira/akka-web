package views.actors;

import actors.core.WelcomeActor;
import actors.messages.AkkaMessages;
import akka.actor.ActorRef;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import views.factories.ActorsViewFactory;

/**
 * Created by zua on 28.08.16.
 */
public class WelcomeActorView extends ActorView {

    public static final String NAME = "WelcomeActorView";

    /**
     * Creates a view for the {@link WelcomeActor}.
     *
     * @see {@link WelcomeActor}, {@link ActorView}.
     */
    public WelcomeActorView() {
        super(WelcomeActor.class);
        addMessage(AkkaMessages.REGISTER, true);
        addMessage(AkkaMessages.LOGIN, true);
        setStyleName(StyleClassNames.WELCOME_ACTOR);
        setId(StyleClassNames.WELCOME_ACTOR);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton().getCaption().equals(AkkaMessages.REGISTER)) {
            getLog().info("REGISTER button clicked");
            getActorRef().tell(AkkaMessages.REGISTER, ActorRef.noSender());
            getUI().setContent(ActorsViewFactory.getInstance().getRegisterActorView());
        }
        if (event.getButton().getCaption().equals(AkkaMessages.LOGIN)) {
            getLog().info("LOGIN button clicked");
            getActorRef().tell(AkkaMessages.LOGIN, ActorRef.noSender());
            getUI().setContent(ActorsViewFactory.getInstance().getLoginActorView());
        }
    }

    @Override
    protected Component createActorContent() {
        return new HorizontalLayout();
    }

    public void setUIContentToRegisterActorView() {
        getUI().setContent(ActorsViewFactory.getInstance().getRegisterActorView());
    }

    public void setUIContentToLoginActorView() {
        getUI().setContent(ActorsViewFactory.getInstance().getLoginActorView());
    }
}
