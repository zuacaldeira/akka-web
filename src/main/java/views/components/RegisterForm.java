package views.components;

import actors.messages.AkkaMessages;
import akka.actor.ActorRef;
import com.vaadin.data.Property;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import views.actors.RegisterActorView;
import views.actors.StyleClassNames;
import views.components.validators.FullNameValidator;
import views.components.validators.PasswordConfirmationValidator;
import views.components.validators.PasswordValidator;

import java.util.Objects;


/**
 * Created by zua on 29.08.16.
 */
public class RegisterForm extends ActorForm implements Property.ValueChangeListener {
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
        emailField.addStyleName(StyleClassNames.EMAIL);

        fullnameField = new TextField("Fullname");
        fullnameField.addValidator(new FullNameValidator("Invalid full name"));
        fullnameField.setRequired(true);
        fullnameField.setValidationVisible(true);
        fullnameField.addStyleName(StyleClassNames.FULLNAME);

        passwordField = new PasswordField("Password");
        passwordField.addValidator(new PasswordValidator("Invalid password"));
        passwordField.setRequired(true);
        passwordField.setValidationVisible(true);
        passwordField.addStyleName(StyleClassNames.PASSWORD);

        passwordConfirmationField = new PasswordField("Confirmation");
        passwordConfirmationField.addValidator(new PasswordConfirmationValidator("Invalid password confirmation", passwordField));
        passwordConfirmationField.setRequired(true);
        passwordConfirmationField.setValidationVisible(true);
        passwordConfirmationField.addStyleName(StyleClassNames.PASSWORD_CONFIRMATION);

        emailField.addValueChangeListener(this);
        fullnameField.addValueChangeListener(this);
        passwordField.addValueChangeListener(this);
        passwordConfirmationField.addValueChangeListener(this);

        addComponents(
                fullnameField,
                emailField,
                passwordField,
                passwordConfirmationField
        );

        //setStyleName(StyleClassNames.REGISTER_FORM);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterForm)) {
            return false;
        }
        RegisterForm that = (RegisterForm) o;
        return  Objects.equals(getActor(), that.getActor()) &&
                Objects.equals(passwordField.getValue(), that.passwordField.getValue()) &&
                Objects.equals(emailField.getValue(), that.emailField.getValue()) &&
                Objects.equals(fullnameField.getValue(), that.fullnameField.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),
                            passwordField.getValue(),
                            emailField.getValue(),
                            fullnameField.getValue(),
                            passwordConfirmationField.getValue()
        );
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
        passwordConfirmationField.validate();
        fullnameField.validate();
    }

    public void validate() {
        validate(null);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        try {
            validate();
            if(getParent() instanceof RegisterActorView) {
                ((RegisterActorView) getParent()).getMessage(AkkaMessages.REGISTER).setEnabled(true);
                ((RegisterActorView) getParent()).getMessage(AkkaMessages.REGISTER).addStyleName(StyleClassNames.ENABLED);
            }
        }
        catch (Exception e) {
            if(getParent() instanceof RegisterActorView) {
                ((RegisterActorView) getParent()).getMessage(AkkaMessages.REGISTER).setEnabled(false);
                ((RegisterActorView) getParent()).getMessage(AkkaMessages.REGISTER).removeStyleName(StyleClassNames.ENABLED);

            }
        }
    }
}
