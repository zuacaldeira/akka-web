package views.actors;

import actors.core.UserActor;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

import java.util.List;

/**
 * Created by zua on 08.09.16.
 */
public class UserActorView extends ActorView {
    public static final String NAME = "UserActorView";

    /**
     * Basic actor view.
     */
    public UserActorView() {
        super(UserActor.class);
    }

    @Override
    protected Component createActorContent() {
        return null;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

    }

}
