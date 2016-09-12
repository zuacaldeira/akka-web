package views.actors;

import actors.core.LoginActor;
import actors.messages.AkkaMessages;
import actors.messages.LoginMessage;
import akka.actor.ActorRef;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
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
        addMessage(AkkaMessages.LOGIN, false);
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
        if (event.getButton().getCaption().equals(AkkaMessages.CANCEL)){
            cleanLoginForm();
            navigateToWelcomePage();
        }

        else if(!isFormEdited()) {
            getLog().info("Empty form: going back to welcome page");
            getUI().setContent(ActorsViewFactory.getInstance().getWelcomeActorView());
        }

        else if (event.getButton().getCaption().equals(AkkaMessages.LOGIN)) {
            /* Asks the login actor to login a user, and waits for the response */
            try {
                login();
                getLog().info("Login successful: moving to user area");
                //getUI().getPage().setLocation("/user");
            } catch (Exception e) {
                getLog().info("Login failed: " + e.getMessage());
                navigateToWelcomePage();
            }
        }
    }

    private void navigateToWelcomePage() {
        getUI().setContent(ActorsViewFactory.getInstance().getWelcomeActorView());
    }

    private void login() {
        Object result = null;
        try {
            loginForm.validate();
            tellToLogin();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void tellToLogin() {
        LoginMessage message = new LoginMessage(loginForm.getEmailField().getValue(), loginForm.getPasswordField().getValue());
        getActorRef().tell(message, ActorRef.noSender());
    }

    private boolean isFormEdited() {
        return !loginForm.getEmailField().getValue().isEmpty()
                || !loginForm.getPasswordField().getValue().isEmpty();
    }

    private Object askToLogin() throws Exception {
        Timeout timeout = new Timeout(Duration.create(1, "minute"));
        LoginMessage message = new LoginMessage(loginForm.getEmailField().getValue(), loginForm.getPasswordField().getValue());
        Future<Object> future = Patterns.ask(getActorRef(), message, timeout);
        Object result = Await.result(future, timeout.duration());
        return result;
    }

    private void cleanLoginForm() {
        loginForm.getEmailField().clear();
        loginForm.getPasswordField().clear();
    }
}
