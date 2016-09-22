package views.actors;

import actors.messages.AkkaMessage;
import actors.mvc.WelcomeMVCActor;
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
     * Creates a view for the {@link WelcomeMVCActor}.
     *
     * @see {@link WelcomeMVCActor}, {@link ActorView}.
     */
    public WelcomeActorView() {
        super(WelcomeMVCActor.class);
        addMessage(AkkaMessage.REGISTER.name(), true);
        addMessage(AkkaMessage.LOGIN.name(), true);
        setStyleName(StyleClassNames.WELCOME_ACTOR.getStyle());
        setId(StyleClassNames.WELCOME_ACTOR.getStyle());
        setDebugId(StyleClassNames.WELCOME_ACTOR.getStyle());
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton().getCaption().equals(AkkaMessage.REGISTER.name())) {
            getLog().info("REGISTER button clicked");
            if(getUI() != null) {
                getActorRef().tell(AkkaMessage.REGISTER, ActorRef.noSender());
                getUI().setContent(ActorsViewFactory.getInstance().getRegisterActorView());
            }
        }
        if (event.getButton().getCaption().equals(AkkaMessage.LOGIN.name())) {
            getLog().info("LOGIN button clicked");
            if(getUI() != null) {
                getActorRef().tell(AkkaMessage.LOGIN, ActorRef.noSender());
                getUI().setContent(ActorsViewFactory.getInstance().getLoginActorView());
            }
        }
    }

    @Override
    protected Component createActorContent() {
        return new HorizontalLayout();
    }

}
