package actors.mvc;

import actors.messages.crud.CreateMessage;
import actors.messages.crud.DeleteMessage;
import actors.messages.crud.ReadMessage;
import actors.messages.crud.UpdateMessage;

/**
 * Created by zua on 26.09.16.
 */
public abstract class CRUDActor<T> extends MVCActor {

    public  void create(CreateMessage<T> message){}
    public  T read(ReadMessage<Long> message){return null;}
    public  void update(UpdateMessage<T> t){}
    public  T delete(DeleteMessage<T> t){return null; }

    @Override
    public void onReceive(Object message) {
        if(message instanceof CreateMessage) {
            create((CreateMessage)message);
        }
        else if(message instanceof ReadMessage) {
            read((ReadMessage)message);
        }
        if(message instanceof UpdateMessage) {
            update((UpdateMessage)message);
        }
        if(message instanceof DeleteMessage) {
            delete((DeleteMessage)message);
        }
        else {
            super.onReceive(message);
        }
    }
}
