package views.factories;

import actors.messages.LoginMessage;
import akka.actor.ActorRef;
import com.vaadin.ui.*;

/**
 * Created by zua on 29.08.16.
 */
public class LoginForm extends ActorForm implements Button.ClickListener {
    private final TextField emailField;
    private final PasswordField passwordField;
    private final SendButton send;
    private final CancelButton cancel;

    public LoginForm(ActorRef actor) {
        super(actor);
        emailField = new TextField("email");
        passwordField = new PasswordField("Password");
        cancel = new CancelButton();
        send = new SendButton();
        cancel.addClickListener(this);
        send.addClickListener(this);

        addComponents(emailField, passwordField, new HorizontalLayout(cancel, send));
    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        if(clickEvent.getButton() == cancel) {
            emailField.clear();
            passwordField.clear();
            ((Window)getParent()).close();
        }
        else if(clickEvent.getButton().equals(send)) {
            getActor().tell(new LoginMessage(emailField.getValue(), passwordField.getValue()), getActor());
            ((Window)getParent()).close();
        }
    }

}
