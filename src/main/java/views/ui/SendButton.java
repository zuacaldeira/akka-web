package views.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

/**
 * Created by zua on 29.08.16.
 */
public class SendButton extends Button {

    public SendButton() {
        super("Send");
        setIcon(FontAwesome.SEND);
    }
}
