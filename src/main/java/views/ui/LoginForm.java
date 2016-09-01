package views.ui;

import actors.messages.LoginMessage;
import akka.actor.ActorRef;
import com.vaadin.ui.*;

import java.util.Objects;

/**
 * Created by zua on 29.08.16.
 */
public class LoginForm extends ActorForm implements Button.ClickListener {
    private final TextField emailField;
    private final PasswordField passwordField;
    private final SendButton send;
    private final CancelButton cancel;

    /**
     * Creates a new Login form, with a reference to the actor responsible for logins.
     *
     * @param actor {@link ActorRef} The actor responsible for loging users in.
     */
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

    public TextField getEmailField() {
        return emailField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public SendButton getSend() {
        return send;
    }

    public CancelButton getCancel() {
        return cancel;
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        if(clickEvent.getButton() == send) {
            getActor().tell(new LoginMessage(emailField.getValue(), passwordField.getValue()), getActor());
        }
        emailField.clear();
        passwordField.clear();
        ((Window)getParent()).close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoginForm)) {
            return false;
        }

        LoginForm that = (LoginForm) o;
        return Objects.equals(emailField.getValue(), that.emailField.getValue()) &&
                Objects.equals(passwordField.getValue(), that.passwordField.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), emailField.getValue().hashCode(), passwordField.getValue().hashCode());
    }
}
