package views.actors;

import actors.core.LoginActor;
import actors.messages.AkkaMessages;
import actors.messages.LoginMessage;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import views.factories.ActorsViewFactory;
import views.ui.LoginForm;

/**
 * Created by zua on 02.09.16.
 */
public class LoginActorView extends ActorView {

    private LoginForm loginForm;

    /**
     * Login actor view.
     */
    public LoginActorView() {
        super(LoginActor.class, AkkaMessages.getLoginActorMessages());
    }

    @Override
    protected void addContent() {
        loginForm = new views.ui.LoginForm(getActorRef());
        loginForm.setSizeUndefined();
        addComponent(loginForm);
        setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton().getCaption().equals(AkkaMessages.LOGIN)) {
            getActorRef().tell(
                new LoginMessage(loginForm.getEmailField().getValue(), loginForm.getPasswordField().getValue()),
                null);
            getUI().setContent(ActorsViewFactory.getInstance().getWelcomeActorView());
        }
    }
}
