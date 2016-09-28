package actors.messages.crud;

import graphs.entities.Entity;

/**
 * Created by zua on 26.09.16.
 */
public class CreateMessage<T extends Entity> extends CrudMessage<T> {

    public CreateMessage(T value) {
        super(value);
    }

    @Override
    public String toString() {
        return "CreateMessage{}" + super.toString();
    }
}
