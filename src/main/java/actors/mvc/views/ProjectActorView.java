package actors.mvc.views;

import actors.messages.ControlMessage;
import actors.messages.world.LeaveAkkaria;
import com.vaadin.ui.*;

/**
 * Created by zua on 26.09.16.
 */
public class ProjectActorView extends ActorView {


    public ProjectActorView() {
        addMailbox(ControlMessage.CANCELLED, true);
        addMailbox(ControlMessage.CREATE, true);
        addMailbox(ControlMessage.READ, true);
        addMailbox(ControlMessage.UPDATE, true);
        addMailbox(ControlMessage.DELETE, true);
    }

    @Override
    protected Component createActorContent() {
        return new HorizontalLayout(new Label("Hi, I am Akkar, your virtual life architect. What do you want to do?"));
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        Notification.show("Clicked " + event.getButton().getCaption());
        if(event.getButton().getCaption().equals(ControlMessage.CANCELLED.name())) {
            getUI().getMVCActor().tell(new LeaveAkkaria(getUI(), ControlMessage.CANCELLED), getUI().getMVCActor());
        }
        else if(event.getButton().getCaption().equals(ControlMessage.CREATE.name())) {
            getUI().getMVCActor().tell(ControlMessage.CREATE, getUI().getMVCActor());
        }
        else if(event.getButton().getCaption().equals(ControlMessage.READ.name())) {
            getUI().getMVCActor().tell(ControlMessage.READ, getUI().getMVCActor());
        }
        else if(event.getButton().getCaption().equals(ControlMessage.UPDATE.name())) {
            getUI().getMVCActor().tell(ControlMessage.UPDATE, getUI().getMVCActor());
        }
        else if(event.getButton().getCaption().equals(ControlMessage.DELETE.name())) {
            getUI().getMVCActor().tell(ControlMessage.DELETE, getUI().getMVCActor());
        }
    }
}
