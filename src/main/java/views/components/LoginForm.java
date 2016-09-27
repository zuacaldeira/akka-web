package views.components;

import actors.messages.ControlMessage;
import com.vaadin.data.Property;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import actors.mvc.views.LoginActorView;
import actors.mvc.views.StyleClassNames;
import views.components.validators.PasswordValidator;

import java.util.Objects;

/**
 * Created by zua on 29.08.16.
 */
public class LoginForm extends ActorForm implements Property.ValueChangeListener {
    private  TextField emailField;
    private PasswordField passwordField;

    /**
     * Creates a new Login form, with a reference to the actor responsible for logins.
     *
     */
    public LoginForm() {
        initEmailField();
        initPasswordField();
        emailField.addValueChangeListener(this);
        passwordField.addValueChangeListener(this);
        addComponents(emailField, passwordField);
    }

    private void initEmailField() {
        emailField = new TextField("email");
        emailField.setRequired(true);
        emailField.addValidator(new EmailValidator("Invalid Email"));
        emailField.setStyleName(StyleClassNames.EMAIL.getStyle());
    }

    private void initPasswordField() {
        passwordField = new PasswordField("Password");
        passwordField.setRequired(true);
        passwordField.addValidator(new PasswordValidator("Invalid password"));
        passwordField.setStyleName(StyleClassNames.PASSWORD.getStyle());
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
        return Objects.hash(emailField.getValue(), passwordField.getValue());
    }

    @Override
    public void validate(Object value) throws InvalidValueException {
        emailField.validate();
        passwordField.validate();
    }

    public void validate() {
        validate(null);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        try {
            validate();
            if(getParent() != null && getParent() instanceof LoginActorView) {
                LoginActorView parent = ((LoginActorView) getParent());
                parent.getMailbox(ControlMessage.LOGIN).setEnabled(true);
                parent.getMailbox(ControlMessage.LOGIN).addStyleName(StyleClassNames.ENABLED.getStyle());
            }
        } catch (InvalidValueException ivx) {
            if(getParent() != null && getParent() instanceof LoginActorView) {
                ((LoginActorView) getParent()).getMailbox(ControlMessage.LOGIN).setEnabled(false);
                ((LoginActorView) getParent()).getMailbox(ControlMessage.LOGIN).removeStyleName(StyleClassNames.ENABLED.getStyle());
            }
        }
    }
}
