package views.factories;

import akka.actor.ActorRef;
import actors.messages.AkkaMessages;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by zua on 29.08.16.
 */
public class MessageView extends Button implements Button.ClickListener, LayoutEvents.LayoutClickListener {
    private final Object content;
    private final ActorRef actor;

    public MessageView(ActorRef actor, Object content) {
        this.content = content;
        this.actor = actor;
        setCaption(content.toString());
        setIcon(FontAwesome.ENVELOPE);
        setSizeUndefined();
        setStyleName(ValoTheme.BUTTON_BORDERLESS);
        addClickListener(this);
    }

    @Override
    public void buttonClick(ClickEvent clickEvent) {
        Window w = new Window(content.toString());
        switch (content.toString()) {
            case AkkaMessages.REGISTER:
                w.setContent(new RegisterForm(actor));
                break;
            case AkkaMessages.LOGIN:
                w.setContent(new LoginForm(actor));
                break;
            default:
                w.setContent(new Label("Unknown"));
                break;
        }

        w.center();
        getUI().addWindow(w);
    }

    @Override
    public void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {

    }
}
