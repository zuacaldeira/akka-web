package views.components;

import akka.actor.ActorRef;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import views.components.validators.FullNameValidator;
import views.components.validators.PasswordConfirmationValidator;
import views.components.validators.PasswordValidator;

import java.util.Objects;


/**
 * Created by zua on 29.08.16.
 */
public class RegisterForm extends ActorForm {
    private final PasswordField passwordField;
    private final TextField emailField;
    private final TextField fullnameField;
    private final PasswordField passwordConfirmationField;

    /**
     * Registration process form.
     *
     * @param actor The actor responsible to start the execution process
     */
    public RegisterForm(ActorRef actor) {
        super(actor);

        emailField = new TextField("Email");
        emailField.addValidator(new EmailValidator("Invalid email"));
        emailField.setRequired(true);
        emailField.setValidationVisible(true);

        fullnameField = new TextField("Fullname");
        fullnameField.addValidator(new FullNameValidator("Invalid full name"));
        fullnameField.setRequired(true);
        fullnameField.setValidationVisible(true);

        passwordField = new PasswordField("Password");
        passwordField.addValidator(new PasswordValidator("Invalid password"));
        passwordField.setRequired(true);
        passwordField.setValidationVisible(true);

        passwordConfirmationField = new PasswordField("Confirmation");
        passwordConfirmationField.addValidator(new PasswordConfirmationValidator("Invalid password confirmation", passwordField));
        passwordConfirmationField.setRequired(true);
        passwordConfirmationField.setValidationVisible(true);

        addComponents(
                fullnameField,
                emailField,
                passwordField,
                passwordConfirmationField
        );
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
        return Objects.hash(passwordField.getValue(), emailField.getValue(), fullnameField.getValue());
    }

    public TextField getEmailField() {
        return emailField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public TextField getFullName() {
        return fullnameField;
    }

    @Override
    public void validate(Object value) throws InvalidValueException {
        emailField.validate();
        passwordField.validate();
        fullnameField.validate();
    }

    public void validate() {
        validate(null);
    }
}
