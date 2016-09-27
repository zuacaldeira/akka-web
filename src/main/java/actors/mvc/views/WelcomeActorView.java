package actors.mvc.views;

import actors.messages.ControlMessage;
import actors.mvc.WelcomeActor;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

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
        super();
        addMailbox(ControlMessage.REGISTER, true);
        addMailbox(ControlMessage.LOGIN, true);
        addStyleName(StyleClassNames.WELCOME_ACTOR.getStyle());
        setId(StyleClassNames.WELCOME_ACTOR.getStyle());
        setDebugId(StyleClassNames.WELCOME_ACTOR.getStyle());
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton().getCaption().equals(ControlMessage.REGISTER.name())) {
            getLog().info("REGISTER button clicked");
                getUI().getMVCActor().tell(ControlMessage.REGISTER, getUI().getMVCActor());
        }
        else if (event.getButton().getCaption().equals(ControlMessage.LOGIN.name())) {
            getLog().info("LOGIN button clicked");
                getUI().getMVCActor().tell(ControlMessage.LOGIN, getUI().getMVCActor());
        }
    }

    @Override
    protected Component createActorContent() {
        return new HorizontalLayout(new Label("Hello, Welcome to Akkaria!"));
    }

}
