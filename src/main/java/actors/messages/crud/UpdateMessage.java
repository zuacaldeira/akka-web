package actors.messages.crud;

/**
 * Created by zua on 26.09.16.
 */
public class UpdateMessage<T> extends CrudMessage<T> {

    public UpdateMessage(T value) {
        super(value);
    }
}
