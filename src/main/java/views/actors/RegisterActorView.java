package views.actors;

import actors.core.IllegalRegistrationException;
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
import views.ui.RegisterForm;

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
        if (event.getButton().getCaption().equals(AkkaMessages.REGISTER)) {
            try {
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
                Notification.show("ERROR: " + irx.getMessage(), Notification.Type.ERROR_MESSAGE);
                irx.printStackTrace();
            } catch (Exception e) {
                Notification.show("ERROR: " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                e.printStackTrace();
            }
            cleanRegisterForm();
        }

        else if (event.getButton().getCaption().equals(AkkaMessages.CANCEL)){
            getUI().setContent(ActorsViewFactory.getInstance().getWelcomeActorView());
        }
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
