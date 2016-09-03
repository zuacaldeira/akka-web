package views.actors;

import actors.core.RegisterActor;
import actors.messages.AkkaMessages;
import actors.messages.RegisterMessage;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
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
        super(RegisterActor.class, AkkaMessages.getRegisterActorMessages());    }

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
            getActorRef().tell(
                    new RegisterMessage(registerForm.getEmailField().getValue(), registerForm.getPasswordField().getValue(), registerForm.getFullName().getValue()),
                    null);
            getUI().setContent(ActorsViewFactory.getInstance().getLoginActorView());
        }
    }
}
