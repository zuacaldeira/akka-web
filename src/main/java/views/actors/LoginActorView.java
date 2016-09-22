package views.actors;

import actors.business.LoginActor;
import actors.messages.AkkaMessage;
import actors.messages.LoginMessage;
import akka.actor.ActorRef;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import views.components.LoginForm;
import views.factories.ActorsViewFactory;

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
        super(LoginActor.class);
        addCancelButton();
        addMessage(AkkaMessage.LOGIN.name(), false);
        getActorRef().tell(this, ActorRef.noSender());
    }

    @Override
    protected Component createActorContent() {
        loginForm = new LoginForm(getActorRef());
        loginForm.setWidth("50%");
        return loginForm;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton().getCaption().equals(AkkaMessage.CANCEL.name())){
            cleanLoginForm();
            navigateToWelcomePage();
        }

        else if(!isFormEdited()) {
            getLog().info("Empty form: going back to welcome page");
            getUI().setContent(ActorsViewFactory.getInstance().getWelcomeActorView());
        }

        else if (event.getButton().getCaption().equals(AkkaMessage.LOGIN.name())) {
            /* Asks the login actor to login a user, and waits for the response */
                login();
                getLog().info("Login successful: moving to user area");
                navigateToWelcomePage();
        }
    }

    private void navigateToWelcomePage() {
        getUI().setContent(ActorsViewFactory.getInstance().getWelcomeActorView());
    }

    private void login() {
        loginForm.validate();
        tellToLogin();
    }

    private void tellToLogin() {
        LoginMessage message = new LoginMessage(loginForm.getEmailField().getValue(), loginForm.getPasswordField().getValue());
        getUI().getMVCActor().tell(message, ActorRef.noSender());
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
