package actors.messages.crud;

/**
 * Created by zua on 26.09.16.
 */
public abstract class  CrudMessage<T> {
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

}
