package views.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

/**
 * Created by zua on 29.08.16.
 */
public class SendButton extends Button {

    /**
     * Creates a send button, to be used globally in the application.
     */
    public SendButton() {
        super("Send");
        setIcon(FontAwesome.SEND);
    }
}
