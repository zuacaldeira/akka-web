package views.components;

import com.vaadin.ui.HorizontalLayout;

/**
 * Created by zua on 10.09.16.
 */
public class Mailboxes extends HorizontalLayout {

    public Mailboxes() {
        setSizeFull();
        setWidthUndefined();
        setSpacing(true);
    }

    public Mailbox getMailbox(int i) {
        return (Mailbox) getComponent(i);
    }
}
