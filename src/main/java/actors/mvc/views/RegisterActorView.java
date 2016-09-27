package actors.mvc.views;

import actors.messages.ControlMessage;
import actors.messages.world.LeaveAkkaria;
import actors.messages.RegisterMessage;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import views.components.RegisterForm;

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
        super();
        addCancelButton();
        addMailbox(ControlMessage.REGISTER, false);
        addStyleName(StyleClassNames.REGISTER_ACTOR.getStyle());
        setId(StyleClassNames.REGISTER_ACTOR.getStyle());
    }

    @Override
    protected Component createActorContent() {
        registerForm = new RegisterForm();
        registerForm.setWidth("100%");
        return registerForm;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton().getCaption().equals(ControlMessage.CANCELLED.name())){
            cleanRegisterForm();
            getUI().getMVCActor().tell(new LeaveAkkaria(getUI(), ControlMessage.CANCELLED), getUI().getMVCActor());
        }

        else if(!isFormEdited()) {
            Notification.show("Empty form", Notification.Type.WARNING_MESSAGE);
            getLog().info("Empty form");
        }

        else if (event.getButton().getCaption().equals(ControlMessage.REGISTER.name())) {
            getUI().getMVCActor().tell(createRegisterMessage(), getUI().getMVCActor());
        }
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
