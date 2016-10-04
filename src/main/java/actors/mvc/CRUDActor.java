package actors.mvc;

import actors.exceptions.SystemFailure;
import actors.messages.ControlMessage;
import actors.messages.crud.*;
import graphs.Neo4jSessionFactory;
import graphs.entities.Entity;
import graphs.entities.User;
import org.neo4j.ogm.session.Session;
import views.ui.AkkaUI;

/**
 * Created by zua on 26.09.16.
 */
public abstract class CRUDActor<T extends Entity> extends MVCActor {

    protected CRUDActor(AkkaUI ui, User user) {
        super(ui, user);
    }

    @Override
    public void onReceive(Object message) {
        if(message instanceof CrudMessage) {
            crud((CrudMessage) message);
            getSender().tell(ControlMessage.SUCCESS, getSelf());
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
        leaveAkkariaOnSuccess(message.getPayload());
    }

    public  void create(CreateMessage<T> message){
        try{
            Neo4jSessionFactory.getInstance().getNeo4jSession().save(message.getPayload());
        } catch (Exception ex) {
            log.error("Met Exception " + ex.getMessage());
            SystemFailure failure = new SystemFailure(ex.getMessage());
            leaveAkkariaOnFailure(failure);
            throw failure;
        }
    }

    public  T read(ReadMessage<T> message){
        try{
            Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
            return session.load(message.getType(), message.getId());
        } catch (Exception ex) {
            log.error("Met Exception " + ex.getMessage());
            SystemFailure failure = new SystemFailure(ex.getMessage());
            leaveAkkariaOnFailure(failure);
            throw failure;
        }
    }

    public  void update(UpdateMessage<T> message){
        try{
            Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
            session.save(message.getPayload());
        } catch (Exception ex) {
            log.error("Met Exception " + ex.getMessage());
            SystemFailure failure = new SystemFailure(ex.getMessage());
            leaveAkkariaOnFailure(failure);
            throw failure;
        }
    }

    public  void delete(DeleteMessage<T> message){
        try{
            Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
            session.delete(message.getPayload());
        } catch (Exception ex) {
            log.error("Met Exception " + ex.getMessage());
            SystemFailure failure = new SystemFailure(ex.getMessage());
            leaveAkkariaOnFailure(failure);
            throw failure;
        }
    }


}
