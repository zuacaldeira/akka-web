package views.components.validators;

import actors.business.AkkarianPasswordValidator;
import com.vaadin.data.Validator;

/**
 * Created by zua on 03.09.16.
 */
public class PasswordValidator implements Validator {
    private final String message;

    public PasswordValidator(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void validate(Object value)  {
        if(!new AkkarianPasswordValidator().isValid((String) value)) {
            throw new InvalidValueException((String)value);
        }
    }
}
