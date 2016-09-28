package actors.mvc;

import actors.messages.ControlMessage;
import actors.messages.crud.*;
import graphs.entities.Entity;

/**
 * Created by zua on 26.09.16.
 */
public abstract class CRUDActor<T extends Entity> extends MVCActor {

    public  void create(CreateMessage<T> message){}
    public  T read(ReadMessage<T> message){return null;}
    public  void update(UpdateMessage<T> t){}
    public  T delete(DeleteMessage<T> t){return null; }

    @Override
    public void onReceive(Object message) {
        if(message instanceof CrudMessage) {
            crud((CrudMessage) message);
            getSender().tell(ControlMessage.SUCCESSFUL, getSelf());
        }
        else {
            super.onReceive(message);
        }
    }

    private void crud(CrudMessage message) {
        if(message instanceof CreateMessage) {
            create((CreateMessage)message);
        }
        else if(message instanceof ReadMessage) {
            read((ReadMessage)message);
        }
        else if(message instanceof UpdateMessage) {
            update((UpdateMessage)message);
        }
        else if(message instanceof DeleteMessage) {
            delete((DeleteMessage)message);
        }
    }
}
