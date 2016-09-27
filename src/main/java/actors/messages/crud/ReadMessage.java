package actors.messages.crud;

/**
 * Created by zua on 26.09.16.
 */
public class ReadMessage<T> extends CrudMessage<Long>{
    public ReadMessage(Long id) {
        super(id);
    }
}
