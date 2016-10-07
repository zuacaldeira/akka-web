package actors.business;

import actors.exceptions.InvalidRegistrationException;
import actors.messages.RegisterMessage;

/**
 * Created by zua on 21.09.16.
 */
public class RegistrationValidator implements AkkarianValidator<RegisterMessage> {

    @Override
    public boolean isValid(RegisterMessage message) {
        boolean validEmail = new UsernameValidator().isValid(message.getEmail());
        if(! validEmail) {
            throw new InvalidRegistrationException("Invalid Email");
        }
        boolean validPassword = new AkkarianPasswordValidator().isValid(message.getPassword());
        if(! validPassword) {
            throw new InvalidRegistrationException("Invalid Password");
        }
        return true;
    }
}
