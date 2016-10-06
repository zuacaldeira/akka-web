package views.components.validators;

import actors.business.AkkarianPasswordValidator;
import actors.business.InvalidPasswordException;
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
        try {
            if(!new AkkarianPasswordValidator().isValid((String) value)) {
                throw new InvalidValueException((String)value);
            }
        } catch (InvalidPasswordException ex) {
            throw new InvalidValueException(ex.getMessage());
        }
    }
}
