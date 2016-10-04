package actors.messages.crud;

import actors.messages.world.AkkaMessage;
import graphs.entities.Entity;

/**
 * Created by zua on 26.09.16.
 */
public abstract class  CrudMessage<T extends Entity> extends AkkaMessage<T> {
    public CrudMessage(T value) {
        super(value);
    }
    public Class<T> getType() {
        return (Class<T>) super.getPayload().getClass();
    }
}
