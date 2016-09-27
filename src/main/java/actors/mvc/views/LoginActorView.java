package actors.mvc.views;

import actors.messages.ControlMessage;
import actors.messages.world.LeaveAkkaria;
import actors.messages.LoginMessage;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import views.components.LoginForm;

/**
 * Created by zua on 02.09.16.
 */
public class LoginActorView extends ActorView {

    public static final String NAME = "LoginActorView";
    private LoginForm loginForm;


    /**
     * Login actor view.
     */
    public LoginActorView() {
        addCancelButton();
        addMailbox(ControlMessage.LOGIN, false);
        addStyleName(StyleClassNames.LOGIN_ACTOR.getStyle());
    }

    @Override
    protected Component createActorContent() {
        loginForm = new LoginForm();
        loginForm.setWidth("50%");
        return loginForm;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton().getCaption().equals(ControlMessage.CANCELLED.name())){
            cleanLoginForm();
            getUI().getMVCActor().tell(new LeaveAkkaria(getUI(), ControlMessage.CANCELLED), getUI().getMVCActor());
        }

        else if (event.getButton().getCaption().equals(ControlMessage.LOGIN.name())) {
            /* Asks the login actor to login a user, and waits for the response */
            loginForm.validate();
            getUI().getMVCActor().tell(createLoginMessage(), getUI().getMVCActor());
        }
    }

    private LoginMessage createLoginMessage() {
        return new LoginMessage(
                loginForm.getEmailField().getValue(),
                loginForm.getPasswordField().getValue());
    }


    protected boolean isFormEdited() {
        return !loginForm.getEmailField().getValue().isEmpty()
                || !loginForm.getPasswordField().getValue().isEmpty();
    }

    private void cleanLoginForm() {
        loginForm.getEmailField().clear();
        loginForm.getPasswordField().clear();
    }
}
