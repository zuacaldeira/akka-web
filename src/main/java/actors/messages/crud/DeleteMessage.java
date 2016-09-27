package actors.messages.crud;

/**
 * Created by zua on 26.09.16.
 */
public class DeleteMessage<T> extends CrudMessage<T> {
    public DeleteMessage(T value) {
        super(value);
    }
}
