package actors.mvc;

import graphs.entities.Project;
import graphs.entities.nodes.User;
import views.ui.AkkaUI;

/**
 * Created by zua on 03.10.16.
 */
public class ProjectDestroyerActor extends CRUDActor<Project> {
    protected ProjectDestroyerActor(AkkaUI ui, User user) {
        super(ui, user);
    }
}
