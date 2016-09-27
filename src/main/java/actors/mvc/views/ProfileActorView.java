package actors.mvc.views;

import actors.messages.ControlMessage;
import actors.messages.crud.UpdateMessage;
import actors.messages.world.LeaveAkkaria;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import graphs.entities.Account;

/**
 * Created by zua on 26.09.16.
 */
public class ProfileActorView extends ActorView {

    public ProfileActorView() {
        addMailbox(ControlMessage.CANCELLED, true);
        addMailbox(ControlMessage.SAVE, true);
    }

    @Override
    protected Component createActorContent() {
        return new HorizontalLayout(new Label("Hi, I am Profilar, your profile manager."));
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if(event.getButton() == getMailbox(ControlMessage.CANCELLED)) {
            getUI().getMVCActor().tell(new LeaveAkkaria(getUI(), ControlMessage.CANCELLED), getUI().getMVCActor());
        }
        else if(event.getButton() == getMailbox(ControlMessage.SAVE)) {
            getUI().getMVCActor().tell(new UpdateMessage<Account>(account), getUI().getMVCActor());
        }
    }
}
