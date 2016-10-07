package actors.messages.crud;

import graphs.entities.Entity;

/**
 * Created by zua on 26.09.16.
 */
public class ReadMessage<T extends Entity> extends CrudMessage<T>{
    private final Long id;

    public ReadMessage(Long id) {
        super(null);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public T getPayload() {
        throw new IllegalStateException("Read message do not carry a value");
    }

    @Override
    public void setPayload(T value) {
        throw new IllegalStateException("Read message do not set any value");
    }

    @Override
    public String toString() {
        return "ReadMessage{" +
                "id=" + id +
                '}';
    }
}
