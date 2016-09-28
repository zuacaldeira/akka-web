package actors.messages.crud;

import graphs.entities.Entity;

/**
 * Created by zua on 26.09.16.
 */
public class DeleteMessage<T extends Entity> extends CrudMessage<T> {
    public DeleteMessage(T value) {
        super(value);
    }
}
