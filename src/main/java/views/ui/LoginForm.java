package views.ui;

import akka.actor.ActorRef;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import java.util.Objects;

/**
 * Created by zua on 29.08.16.
 */
public class LoginForm extends ActorForm {
    private  TextField emailField;
    private PasswordField passwordField;

    /**
     * Creates a new Login form, with a reference to the actor responsible for logins.
     *
     * @param actor {@link ActorRef} The actor responsible for loging users in.
     */
    public LoginForm(ActorRef actor) {
        super(actor);
        initEmailField();
        initPasswordField();
        addComponents(emailField, passwordField);
    }

    private void initEmailField() {
        emailField = new TextField("email");
        emailField.setRequired(true);
        emailField.addValidator(new EmailValidator("Invalid Email"));
    }

    private void initPasswordField() {
        passwordField = new PasswordField("Password");
        passwordField.setRequired(true);
        passwordField.addValidator(new PasswordValidator());
        passwordField.setValidationVisible(true);
    }


    public TextField getEmailField() {
        return emailField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
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
