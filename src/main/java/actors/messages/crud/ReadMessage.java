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
}
