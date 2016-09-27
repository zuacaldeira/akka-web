package actors.mvc.views;

import actors.messages.ControlMessage;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

/**
 * Created by zua on 08.09.16.
 */
public class UserActorView extends ActorView {
    public static final String NAME = "UserActorView";

    /**
     * Basic actor view.
     */
    public UserActorView() {
        addMailbox(ControlMessage.PROFILE, true);
        addMailbox(ControlMessage.PROJECT, true);
    }

    @Override
    protected Component createActorContent() {
        return new Label("Hi, I am your akkar, your avatar in Akkaria");
    }



    @Override
    public void buttonClick(Button.ClickEvent event) {
        if(event.getButton() == getMailbox(ControlMessage.PROFILE)) {
            getUI().getMVCActor().tell(ControlMessage.PROFILE, getUI().getMVCActor());
        }
        else if(event.getButton() == getMailbox(ControlMessage.PROJECT)) {
            getUI().getMVCActor().tell(ControlMessage.PROJECT, getUI().getMVCActor());
        }
    }

}
