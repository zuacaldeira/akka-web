package views.actors;

import akka.actor.ActorRef;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

import java.util.Objects;

/**
 * Created by zua on 29.08.16.
 */
public class MessageView extends Button {
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
        setStyleName("message");
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
