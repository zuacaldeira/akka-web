package views.components;

import actors.messages.ControlMessage;
import com.vaadin.data.Property;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import actors.mvc.views.RegisterActorView;
import actors.mvc.views.StyleClassNames;
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
     * RegisteredAs process form.
     *
     */
    public RegisterForm() {
        super();

        emailField = new TextField("Email");
        emailField.addValidator(new EmailValidator("Invalid email"));
        emailField.setRequired(true);
        emailField.setValidationVisible(true);
        emailField.addStyleName(StyleClassNames.EMAIL.getStyle());

        fullnameField = new TextField("Fullname");
        fullnameField.addValidator(new FullNameValidator("Invalid full name"));
        fullnameField.setRequired(true);
        fullnameField.setValidationVisible(true);
        fullnameField.addStyleName(StyleClassNames.FULLNAME.getStyle());

        passwordField = new PasswordField("Password");
        passwordField.addValidator(new PasswordValidator("Invalid password"));
        passwordField.setRequired(true);
        passwordField.setValidationVisible(true);
        passwordField.addStyleName(StyleClassNames.PASSWORD.getStyle());

        passwordConfirmationField = new PasswordField("Confirmation");
        passwordConfirmationField.addValidator(new PasswordConfirmationValidator("Invalid password confirmation", passwordField));
        passwordConfirmationField.setRequired(true);
        passwordConfirmationField.setValidationVisible(true);
        passwordConfirmationField.addStyleName(StyleClassNames.PASSWORD_CONFIRMATION.getStyle());

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
        return  Objects.equals(passwordField.getValue(), that.passwordField.getValue()) &&
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

    public void validate() {
        emailField.validate();
        passwordField.validate();
        passwordConfirmationField.validate();
        fullnameField.validate();
    }


    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        try {
            validate();
            if(getParent() instanceof RegisterActorView) {
                ((RegisterActorView) getParent()).getMessage(ControlMessage.REGISTER).setEnabled(true);
                ((RegisterActorView) getParent()).getMessage(ControlMessage.REGISTER).addStyleName(StyleClassNames.ENABLED.getStyle());
            }
        }
        catch (Exception e) {
            if(getParent() instanceof RegisterActorView) {
                ((RegisterActorView) getParent()).getMessage(ControlMessage.REGISTER).setEnabled(false);
                ((RegisterActorView) getParent()).getMessage(ControlMessage.REGISTER).removeStyleName(StyleClassNames.ENABLED.getStyle());

            }
        }
    }

    public PasswordField getPasswordConfirmationField() {
        return passwordConfirmationField;
    }
}
