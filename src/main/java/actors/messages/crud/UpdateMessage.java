package actors.messages.crud;

import graphs.entities.Entity;

/**
 * Created by zua on 26.09.16.
 */
public class UpdateMessage<T extends Entity> extends CrudMessage<T> {

    public UpdateMessage(T value) {
        super(value);
    }

}
