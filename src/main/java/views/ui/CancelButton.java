package views.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

/**
 * A cancel button to be used in forms and dialogs.
 *
 * Created by zua on 29.08.16.
 */
public class CancelButton extends Button {
    /**
     * Creates a new cancel button.
     */
    public CancelButton() {
        super("Cancel");
        setIcon(FontAwesome.TIMES);
    }
}
