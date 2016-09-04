package views.components.validators;

import com.vaadin.data.Validator;

/**
 * Created by zua on 03.09.16.
 */
public class PasswordValidator implements Validator {
    private static final int MIN_SIZE = 6;
    private final String message;

    public PasswordValidator(String message) {
        this.message = message;
    }

    @Override
    public void validate(Object value) throws InvalidValueException {
        String prefix = message;
        if(value == null) {
            throw new InvalidValueException(prefix + "null");
        }
        else if(value.toString().isEmpty()) {
            throw new InvalidValueException(prefix + "Password is empty");
        }
        else if(value.toString().length() < MIN_SIZE) {
            throw new InvalidValueException(prefix + "Passwords must have at least " + MIN_SIZE);
        }
    }
}
