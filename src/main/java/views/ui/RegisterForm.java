package views.ui;

import akka.actor.ActorRef;
import actors.messages.RegisterMessage;
import com.vaadin.ui.*;

import java.util.Objects;


/**
 * Created by zua on 29.08.16.
 */
public class RegisterForm extends ActorForm implements Button.ClickListener {
    private final PasswordField passwordField;
    private final TextField emailField;
    private final CancelButton cancel;
    private final SendButton send;
    private final TextField fullnameField;

    /**
     * Registration process form.
     *
     * @param actor The actor responsible to start the execution process
     */
    public RegisterForm(ActorRef actor) {
        super(actor);

        emailField = new TextField("Email");
        fullnameField = new TextField("Fullname");
        passwordField = new PasswordField("Password");

        cancel = new CancelButton();
        cancel.addClickListener(this);

        send = new SendButton();
        send.addClickListener(this);

        addComponents(
                fullnameField,
                emailField,
                passwordField,
                new HorizontalLayout(cancel, send)
        );
    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        if(clickEvent.getButton() == cancel) {
            emailField.clear();
            passwordField.clear();
            ((Window)getParent()).close();
        }
        else if(clickEvent.getButton().equals(send)) {
            getActor().tell(new RegisterMessage(emailField.getValue(), passwordField.getValue(), fullnameField.getValue()), getActor());
            ((Window)getParent()).close();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterForm)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        RegisterForm that = (RegisterForm) o;
        return Objects.equals(passwordField, that.passwordField) &&
                Objects.equals(emailField, that.emailField) &&
                Objects.equals(fullnameField, that.fullnameField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passwordField, emailField, fullnameField);
    }
}
