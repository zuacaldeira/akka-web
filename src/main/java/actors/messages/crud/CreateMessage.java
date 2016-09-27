package actors.messages.crud;

/**
 * Created by zua on 26.09.16.
 */
public class CreateMessage<T> extends CrudMessage<T> {

    public CreateMessage(T value) {
        super(value);
    }
}
