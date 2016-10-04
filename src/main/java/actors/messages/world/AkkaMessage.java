package actors.messages.world;

/**
 * Created by zua on 03.10.16.
 */
public abstract class AkkaMessage<T> {
    private T payload;
    public AkkaMessage(T payload) {
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
