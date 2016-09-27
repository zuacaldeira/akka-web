package actors.mvc.views;

import actors.messages.ControlMessage;
import com.vaadin.ui.*;

/**
 * Created by zua on 26.09.16.
 */
public class ProjectActorView extends ActorView {


    public ProjectActorView() {
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
        Notification.show("Clicked " + event.getButton());
    }
}
