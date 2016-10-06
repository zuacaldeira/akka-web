package actors.mvc;

import graphs.entities.Project;
import graphs.entities.nodes.User;
import views.ui.AkkaUI;

/**
 * Created by zua on 03.10.16.
 */
public class ProjectUpdaterActor extends CRUDActor<Project> {
    protected ProjectUpdaterActor(AkkaUI ui, User user) {
        super(ui, user);
    }
}
