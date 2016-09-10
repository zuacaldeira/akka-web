package views.actors;

import actors.core.RegisterActor;
import actors.messages.AkkaMessages;
import actors.messages.RegisterMessage;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import views.components.RegisterForm;
import views.factories.ActorsViewFactory;

import java.util.logging.Level;

/**
 * Created by zua on 02.09.16.
 */
public class RegisterActorView extends ActorView {
    public static final String NAME = "RegisterActorView";
    private RegisterForm registerForm;

    /**
     * Register actor view.
     */
    public RegisterActorView() {
        super(RegisterActor.class);
        addCancelButton();
        addMessage(AkkaMessages.REGISTER, false);
        setStyleName(StyleClassNames.REGISTER_ACTOR);
        setId(StyleClassNames.REGISTER_ACTOR);
    }

    @Override
    protected Component createActorContent() {
        registerForm = new RegisterForm(getActorRef());
        registerForm.setWidth("100%");
        return registerForm;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton().getCaption().equals(AkkaMessages.CANCEL)){
            getUI().setContent((Component) ActorsViewFactory.getInstance().getWelcomeActorView());
            cleanRegisterForm();
        }

        else if(!isFormEdited()) {
            Notification.show("Empty form", Notification.Type.WARNING_MESSAGE);
            getLog().info("Empty form");
        }

        else if (event.getButton().getCaption().equals(AkkaMessages.REGISTER)) {
            try {
                Object result = registerNewAccount();
                if(result.equals(AkkaMessages.DONE)) {
                    getLog().info(result.toString());
                    getUI().setContent((Component) ActorsViewFactory.getInstance().getLoginActorView());
                } else {
                    getLog().log(Level.SEVERE, result.toString());
                    getUI().setContent((Component) ActorsViewFactory.getInstance().getWelcomeActorView());
                }
            }catch (Exception e) {
                getLog().log(Level.SEVERE, e.getMessage());
                getUI().setContent((Component) ActorsViewFactory.getInstance().getWelcomeActorView());
            }
        }
    }

    private Object registerNewAccount() throws Exception {
            Timeout timeout = new Timeout(Duration.create(1, "minute"));
            RegisterMessage message = createRegisterMessage();
            Future<Object> future = Patterns.ask(
                    getActorRef(),
                    message,
                    timeout);
            return Await.result(future, timeout.duration());
    }

    private boolean isFormEdited() {
        return !registerForm.getEmailField().getValue().isEmpty()
                || !registerForm.getPasswordField().getValue().isEmpty()
                || !registerForm.getFullName().getValue().isEmpty();
    }

    private RegisterMessage createRegisterMessage() {
        return new RegisterMessage(
            registerForm.getEmailField().getValue(),
            registerForm.getPasswordField().getValue(),
            registerForm.getFullName().getValue());
    }

    private void cleanRegisterForm() {
        registerForm.getEmailField().clear();
        registerForm.getPasswordField().clear();
        registerForm.getFullName().clear();
    }
}
