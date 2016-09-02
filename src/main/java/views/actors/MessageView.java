package views.actors;

import actors.messages.AkkaMessages;
import akka.actor.ActorRef;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import views.factories.ActorsViewFactory;

import java.util.Objects;

/**
 * Created by zua on 29.08.16.
 */
public class MessageView extends Button implements Button.ClickListener {
    private String message;
    private final ActorRef actor;

    /**
     * Creates a message view, for a specific actor and message.
     *
     * @param actor The actor able to receive the message
     * @param message The message
     */
    public MessageView(ActorRef actor, String message) {
        this.message = message;
        this.actor = actor;
        setCaption(message);
        setIcon(FontAwesome.ENVELOPE);
        setSizeUndefined();
        setStyleName("message");
        addClickListener(this);
    }

    @Override
    public void buttonClick(ClickEvent clickEvent) {
        switch (message) {
            case AkkaMessages.REGISTER:
                getUI().setContent(ActorsViewFactory.getInstance().getRegisterActorView());
                break;
            case AkkaMessages.LOGIN:
                getUI().setContent(ActorsViewFactory.getInstance().getLoginActorView());
                break;
            default:
                //getUI().setContent(ActorsViewFactory.getRegisterActor());
                break;
        }
    }

    public ActorRef getActor() {
        return actor;
    }

    public Object getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MessageView)) {
            return false;
        }

        MessageView that = (MessageView) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(actor, that.actor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, actor);
    }
}
