package views.components;

import actors.messages.AkkaMessages;
import akka.actor.ActorRef;
import com.vaadin.data.Property;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import views.actors.LoginActorView;
import views.actors.StyleClassNames;
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
     * @param actor {@link ActorRef} The actor responsible for loging users in.
     */
    public LoginForm(ActorRef actor) {
        super(actor);
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
        emailField.setStyleName(StyleClassNames.EMAIL);
    }

    private void initPasswordField() {
        passwordField = new PasswordField("Password");
        passwordField.setRequired(true);
        passwordField.addValidator(new PasswordValidator("Invalid password"));
        passwordField.setStyleName(StyleClassNames.PASSWORD);
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
            if(getParent() instanceof LoginActorView) {
                ((LoginActorView) getParent()).getMessage(AkkaMessages.LOGIN).setEnabled(true);
                ((LoginActorView) getParent()).getMessage(AkkaMessages.LOGIN).addStyleName(StyleClassNames.ENABLED);
            }
        } catch (InvalidValueException ivx) {
            if(getParent() instanceof LoginActorView) {
                ((LoginActorView) getParent()).getMessage(AkkaMessages.LOGIN).setEnabled(false);
                ((LoginActorView) getParent()).getMessage(AkkaMessages.LOGIN).removeStyleName(StyleClassNames.ENABLED);
            }
        }
    }
}
