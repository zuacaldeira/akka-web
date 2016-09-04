package views.actors;

import actors.core.exceptions.IllegalRegistrationException;
import actors.core.RegisterActor;
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
import views.factories.ActorsViewFactory;
import views.components.RegisterForm;

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
        if (event.getButton().getCaption().equals(AkkaMessages.REGISTER) && isFormEdited()) {
            try {
                registerForm.validate();
                Timeout timeout = new Timeout(Duration.create(1, "minute"));
                RegisterMessage message = createRegisterMessage();
                Future<Object> future = Patterns.ask(
                        getActorRef(),
                        message,
                        timeout);
                Object result = Await.result(future, timeout.duration());
                if(result instanceof IllegalRegistrationException){
                    throw (IllegalRegistrationException) result;
                }
                getUI().setContent(ActorsViewFactory.getInstance().getLoginActorView());
                Notification.show((result != null) ? result.toString(): "NO MESSAGE", Notification.Type.TRAY_NOTIFICATION);
            } catch (IllegalRegistrationException irx) {
                logException(irx);
            } catch (Exception e) {
                logException(new IllegalRegistrationException(e.getMessage()));
            }
            cleanRegisterForm();
        }
        else if (event.getButton().getCaption().equals(AkkaMessages.REGISTER) && !isFormEdited()) {
            Notification.show("Empty form", Notification.Type.ERROR_MESSAGE);
        }

        else if (event.getButton().getCaption().equals(AkkaMessages.CANCEL)){
            getUI().setContent(ActorsViewFactory.getInstance().getWelcomeActorView());
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
