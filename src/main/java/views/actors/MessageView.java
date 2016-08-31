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

/**
 * Created by zua on 29.08.16.
 */
public class MessageView extends Button implements Button.ClickListener {
    private Object content;
    private final ActorRef actor;
    private Window window;

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
        window = new Window(content.toString());
        switch (content.toString()) {
            case AkkaMessages.REGISTER:
                window.setContent(new RegisterForm(actor));
                break;
            case AkkaMessages.LOGIN:
                window.setContent(new LoginForm(actor));
                break;
            default:
                window.setContent(new Label("Unknown"));
                break;
        }

        window.center();
        getUI().addWindow(window);
    }

    public ActorRef getActor() {
        return actor;
    }

    public Object getContent() {
        return content;
    }

    public Window getWindow() {
        return window;
    }
}
