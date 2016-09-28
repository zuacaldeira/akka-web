package actors.messages.crud;

import graphs.entities.Entity;

/**
 * Created by zua on 26.09.16.
 */
public abstract class  CrudMessage<T extends Entity> {
    private T value;

    public CrudMessage(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CrudMessage{" +
                "value=" + value +
                '}';
    }
}
