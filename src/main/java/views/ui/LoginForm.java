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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoginForm)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        LoginForm that = (LoginForm) o;
        return Objects.equals(emailField, that.emailField) &&
                Objects.equals(passwordField, that.passwordField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), emailField, passwordField);
    }
}
