package views.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

/**
 * Created by zua on 29.08.16.
 */
public class CancelButton extends Button {
    public CancelButton() {
        super("Cancel");
        setIcon(FontAwesome.SAVE);
    }
}
