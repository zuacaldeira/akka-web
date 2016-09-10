package views.actors;

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
     *
     * @param actor    The underlying actor
     * @param messages The messages the actor is supposed to process
     */
    public UserActorView(Class<?> actor, List<String> messages) {
        super(actor);
    }

    @Override
    protected Component createActorContent() {
        return null;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

    }

}
