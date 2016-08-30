package views.factories;

import akka.actor.ActorRef;
import com.vaadin.ui.FormLayout;

/**
 * Created by zua on 29.08.16.
 */
public class ActorForm extends FormLayout {
    private final ActorRef actor;

    public ActorForm(ActorRef actor) {
        this.actor = actor;
        setSizeUndefined();
    }

    public ActorRef getActor() {
        return actor;
    }
}
