package views.actors;

import actors.core.RegisterActor;
import actors.core.exceptions.IllegalRegistrationException;
import actors.messages.AkkaMessages;
import actors.messages.RegisterMessage;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import views.components.RegisterForm;
import views.factories.ActorsViewFactory;

/**
 * Created by zua on 02.09.16.
 */
public class RegisterActorView extends ActorView {
    private RegisterForm registerForm;

    /**
     * Register actor view.
     */
    public RegisterActorView() {
        super(RegisterActor.class, AkkaMessages.getRegisterActorMessages());
        addCancelButton();
        setStyleName(StyleClassNames.REGISTER_ACTOR);
        setId(StyleClassNames.REGISTER_ACTOR);
    }

    @Override
    protected void addContent() {
        registerForm = new RegisterForm(getActorRef());
        registerForm.setSizeUndefined();
        addComponent(registerForm);
        setComponentAlignment(registerForm, Alignment.MIDDLE_CENTER);

    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton().getCaption().equals(AkkaMessages.CANCEL)){
            getUI().setContent(ActorsViewFactory.getInstance().getWelcomeActorView());
        }

        else if(!isFormEdited()) {
            Notification.show("Empty form", Notification.Type.TRAY_NOTIFICATION);
            getLog().info("Empty form");
            getUI().setContent(ActorsViewFactory.getInstance().getWelcomeActorView());
        }

        else if (event.getButton().getCaption().equals(AkkaMessages.REGISTER)) {
            registerNewAccount();
            getUI().setContent(ActorsViewFactory.getInstance().getLoginActorView());
        }
    }

    private void registerNewAccount() {
        Object result = null;
        try {
            registerForm.validate();
            Timeout timeout = new Timeout(Duration.create(1, "minute"));
            RegisterMessage message = createRegisterMessage();
            Future<Object> future = Patterns.ask(
                    getActorRef(),
                    message,
                    timeout);
            result = getResultOrException(future, timeout);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(result instanceof IllegalRegistrationException){
            throw (IllegalRegistrationException) result;
        }
    }

    private Object getResultOrException(Future<Object> future, Timeout timeout) {
        try {
            return Await.result(future, timeout.duration());
        } catch (Exception e) {
            return new IllegalRegistrationException(e.getMessage());
        }
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
