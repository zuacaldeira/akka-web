package views.ui;

import actors.messages.RegisterMessage;
import akka.actor.ActorRef;
import com.vaadin.ui.*;
import views.factories.ActorsViewFactory;

import java.util.Objects;


/**
 * Created by zua on 29.08.16.
 */
public class RegisterForm extends ActorForm implements Button.ClickListener {
    private final PasswordField passwordField;
    private final TextField emailField;
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

        addComponents(
                fullnameField,
                emailField,
                passwordField
        );
    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        getActor().tell(new RegisterMessage(emailField.getValue(), passwordField.getValue(), fullnameField.getValue()), getActor());
        getUI().setContent(ActorsViewFactory.getInstance().getLoginActorView());
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

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public TextField getFullName() {
        return fullnameField;
    }
}
