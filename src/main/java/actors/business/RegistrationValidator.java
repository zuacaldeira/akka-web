package actors.business;

import actors.messages.RegisterMessage;

/**
 * Created by zua on 21.09.16.
 */
public class RegistrationValidator {

    public boolean isValid(RegisterMessage message) {
        return new UsernameValidator().isValid(message.getEmail())
                && new PasswordValidator().isValid(message.getPassword());
    }
}
