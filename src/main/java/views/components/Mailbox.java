package views.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Objects;

/**
 * Created by zua on 29.08.16.
 */
public class Mailbox extends Button  {
    private String message;

    /**
     * Creates a message view, for a specific actor and message.
     *
     * @param message The message
     */
    public Mailbox(String message) {
        this.message = message;
        setCaption(message);
        setIcon(FontAwesome.ENVELOPE);
        setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        setId(message);
        setSizeFull();
    }


    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "Mailbox{" +
                "message='" + message + '\'' +
                '}';
    }
}
