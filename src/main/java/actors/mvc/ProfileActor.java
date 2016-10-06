package actors.mvc;

import graphs.entities.nodes.User;
import views.ui.AkkaUI;

/**
 * Created by zua on 26.09.16.
 */
public class ProfileActor extends MVCActor {
    protected ProfileActor(AkkaUI ui, User user) {
        super(ui, user);
    }
}
