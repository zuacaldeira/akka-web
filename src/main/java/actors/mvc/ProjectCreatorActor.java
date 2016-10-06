package actors.mvc;

import actors.messages.crud.CreateMessage;
import actors.messages.crud.CrudMessage;
import graphs.entities.Project;
import graphs.entities.nodes.User;
import graphs.entities.nodes.WorkingOn;
import views.ui.AkkaUI;

/**
 * Created by zua on 03.10.16.
 */
public class ProjectCreatorActor extends CRUDActor<Project> {

    protected ProjectCreatorActor(AkkaUI ui, User user) {
        super(ui, user);
    }

    @Override
    public void onReceive(Object message) {
        if(message instanceof CrudMessage) {
            if(message instanceof CreateMessage) {
                CreateMessage m = (CreateMessage) message;
                super.onReceive(new CreateMessage<WorkingOn>(new WorkingOn(getUser(), (Project) m.getPayload())));
            }
        }
        else {
            super.onReceive(message);
        }
    }


}
