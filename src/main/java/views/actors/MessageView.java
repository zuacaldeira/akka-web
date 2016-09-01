package views.actors;

import actors.messages.AkkaMessages;
import akka.actor.ActorRef;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import views.ui.LoginForm;
import views.ui.RegisterForm;

import java.util.Objects;

/**
 * Created by zua on 29.08.16.
 */
public class MessageView extends Button implements Button.ClickListener {
    private String message;
    private final ActorRef actor;
    private Window window;

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
        setStyleName(ValoTheme.BUTTON_BORDERLESS);
        addClickListener(this);
    }

    @Override
    public void buttonClick(ClickEvent clickEvent) {
        window = new Window(message);
        switch (message) {
            case AkkaMessages.REGISTER:
                window.setContent(new RegisterForm(actor));
                break;
            case AkkaMessages.LOGIN:
                window.setContent(new LoginForm(actor));
                break;
            default:
                window.setContent(new Label(AkkaMessages.UNKNOWN));
                break;
        }

        window.center();
        getUI().addWindow(window);
    }

    public ActorRef getActor() {
        return actor;
    }

    public Object getMessage() {
        return message;
    }

    public Window getWindow() {
        return window;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MessageView)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        MessageView that = (MessageView) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(actor, that.actor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), message, actor);
    }
}
