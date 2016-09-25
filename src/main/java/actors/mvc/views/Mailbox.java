package actors.mvc.views;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Objects;

/**
 * Created by zua on 29.08.16.
 */
public class Mailbox extends Button implements Button.ClickListener {
    private String message;
    private String name;

    /**
     * Creates a message view, for a specific actor and message.
     *
     * @param message The message
     */
    public Mailbox(String message) {
        this.message = message;
        setCaption(message);
        setIcon(FontAwesome.ENVELOPE);
        setStyleName(ValoTheme.BUTTON_SMALL);
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
        if (!(o instanceof Mailbox)) {
            return false;
        }

        Mailbox that = (Mailbox) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public void buttonClick(ClickEvent event) {

    }
}
