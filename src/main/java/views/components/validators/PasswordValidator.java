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
        if(!new actors.business.PasswordValidator().isValid((String) value)) {
            throw new InvalidValueException((String)value);
        }
    }
}
