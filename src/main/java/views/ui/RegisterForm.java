package views.ui;

import actors.messages.RegisterMessage;
import akka.actor.ActorRef;
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
        if(clickEvent.getButton() == send) {
            getActor().tell(new RegisterMessage(emailField.getValue(), passwordField.getValue(), fullnameField.getValue()), getActor());
        }
        emailField.clear();
        passwordField.clear();
        fullnameField.clear();
        ((Window)getParent()).close();
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
        return Objects.equals(passwordField.getValue(), that.passwordField.getValue()) &&
                Objects.equals(emailField.getValue(), that.emailField.getValue()) &&
                Objects.equals(fullnameField.getValue(), that.fullnameField.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passwordField.getValue(), emailField.getValue(), fullnameField.getValue());
    }

    public TextField getEmailField() {
        return emailField;
    }

    public CancelButton getCancel() {
        return cancel;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public SendButton getSend() {
        return send;
    }

    public TextField getFullName() {
        return fullnameField;
    }
}
