package views.actors;

import akka.actor.ActorRef;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Objects;

/**
 * Created by zua on 29.08.16.
 */
public class Mailbox extends Button {
    private String message;
    private final ActorRef actor;
    private String name;

    /**
     * Creates a message view, for a specific actor and message.
     *
     * @param actor The actor able to receive the message
     * @param message The message
     */
    public Mailbox(ActorRef actor, String message) {
        this.message = message;
        this.actor = actor;
        setCaption(message);
        setIcon(FontAwesome.ENVELOPE);
        setStyleName(ValoTheme.BUTTON_SMALL);
        setId(message);
        setSizeFull();
    }

    public ActorRef getActor() {
        return actor;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mailbox)) {
            return false;
        }

        Mailbox that = (Mailbox) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(actor, that.actor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, actor);
    }
}
