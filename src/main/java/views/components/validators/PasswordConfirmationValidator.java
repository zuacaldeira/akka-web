package views.components.validators;

import com.vaadin.ui.PasswordField;

/**
 * Created by zua on 04.09.16.
 */
public class PasswordConfirmationValidator extends PasswordValidator {
    private final PasswordField originalPasswordField;

    public PasswordConfirmationValidator(String message, PasswordField passwordField) {
        super(message);
        this.originalPasswordField = passwordField;
    }

    @Override
    public void validate(Object value) {
        super.validate(value);
        if(value instanceof String) {
            String pass = (String) value;
            if(!pass.equals(originalPasswordField.getValue())) {
                throw new InvalidValueException("Passwords are not equal");
            }
        }
    }
}
