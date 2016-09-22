package views.actors;

import actors.business.RegisterActor;
import actors.messages.AkkaMessage;
import actors.messages.RegisterMessage;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import views.components.AkkaUI;
import views.components.RegisterForm;
import views.factories.ActorsViewFactory;

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
        addMessage(AkkaMessage.REGISTER.name(), false);
        setStyleName(StyleClassNames.REGISTER_ACTOR.getStyle());
        setId(StyleClassNames.REGISTER_ACTOR.getStyle());
    }

    @Override
    protected Component createActorContent() {
        registerForm = new RegisterForm(getActorRef());
        registerForm.setWidth("100%");
        return registerForm;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton().getCaption().equals(AkkaMessage.CANCEL.name())){
            getUI().setContent(ActorsViewFactory.getInstance().getWelcomeActorView());
            cleanRegisterForm();
        }

        else if(!isFormEdited()) {
            Notification.show("Empty form", Notification.Type.WARNING_MESSAGE);
            getLog().info("Empty form");
        }

        else if (event.getButton().getCaption().equals(AkkaMessage.REGISTER.name())) {
            registerNewAccount();
            getUI().setContent(ActorsViewFactory.getInstance().getLoginActorView());
        }
    }

    private void registerNewAccount()  {
        getActorRef().tell(createRegisterMessage(), ((AkkaUI)getUI()).getMVCActor());
    }

    protected boolean isFormEdited() {
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
