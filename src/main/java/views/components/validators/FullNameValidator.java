package views.components.validators;

/**
 * Created by zua on 04.09.16.
 */
public class FullNameValidator implements com.vaadin.data.Validator {
    private final String message;

    public FullNameValidator(String message) {
        this.message = message;
    }

    @Override
    public void validate(Object value) {
        if(value == null) {
            throw new InvalidValueException(message + ": null value");
        }
    }
}
