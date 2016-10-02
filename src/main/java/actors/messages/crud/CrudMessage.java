package actors.messages.crud;

import graphs.entities.Entity;

/**
 * Created by zua on 26.09.16.
 */
public abstract class  CrudMessage<T extends Entity> {
    private Entity value;

    public CrudMessage(T value) {
        this.value = value;
    }

    public T getValue() {
        return (T) value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "value=" + value +
                '}';
    }

    public Class<T> getType() {
        return (Class<T>) getValue().getClass();
    }
}
