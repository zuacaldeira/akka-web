package views.actors;

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

    public void addMailbox(Mailbox mailbox) {
        addComponent(mailbox);
        mailbox.setSizeFull();
    }
}
