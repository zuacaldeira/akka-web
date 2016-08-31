package views.ui;

import akka.actor.ActorRef;

/**
 * Created by zua on 29.08.16.
 */
public class ActorForm extends MyForm {
    private final ActorRef actor;

    public ActorForm(ActorRef actor) {
        this.actor = actor;
        setSizeUndefined();
    }

    public ActorRef getActor() {
        return actor;
    }
}
