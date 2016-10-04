package actors.mvc;

import graphs.entities.User;
import views.ui.AkkaUI;

/**
 * Created by zua on 03.10.16.
 */
public class ProjectReaderActor extends MVCActor {
    protected ProjectReaderActor(AkkaUI ui, User user) {
        super(ui, user);
    }
}
