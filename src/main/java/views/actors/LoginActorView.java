package views.actors;

import actors.core.IllegalLoginException;
import actors.core.LoginActor;
import actors.messages.AkkaMessages;
import actors.messages.LoginMessage;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
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
        addCancelButton();
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
            /* Asks the login actor to login a user, and waits for the response */
            try {
                Timeout timeout = new Timeout(Duration.create(1, "minute"));
                LoginMessage message = new LoginMessage(loginForm.getEmailField().getValue(), loginForm.getPasswordField().getValue());
                Future<Object> future = Patterns.ask(getActorRef(), message, timeout);
                Object result = Await.result(future, timeout.duration());
                if(result instanceof IllegalLoginException){
                    throw (IllegalLoginException) result;
                }
                Notification.show((result != null) ? result.toString(): "NO MESSAGE", Notification.Type.TRAY_NOTIFICATION);
                getUI().getPage().setLocation("/user");
            } catch(IllegalLoginException ilx) {
                Notification.show("ERROR: " + ilx.getMessage(), Notification.Type.ERROR_MESSAGE);
                ilx.printStackTrace();
            } catch (Exception e) {
                Notification.show("ERROR: " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                e.printStackTrace();
                getUI().setContent(ActorsViewFactory.getInstance().getWelcomeActorView());
            }
            cleanLoginForm();
        }
        if (event.getButton().getCaption().equals(AkkaMessages.CANCEL)) {
            getUI().setContent(ActorsViewFactory.getInstance().getWelcomeActorView());
        }

    }

    private void cleanLoginForm() {
        loginForm.getEmailField().clear();
        loginForm.getPasswordField().clear();
    }
}
