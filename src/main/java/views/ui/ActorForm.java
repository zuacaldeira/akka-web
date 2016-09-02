package views.ui;

import akka.actor.ActorRef;

import java.util.Objects;

/**
 * Creates a form that has a relationship with an actor.
 *
 * Created by zua on 29.08.16.
 */
public class ActorForm extends MyForm {
    private final ActorRef actor;

    /**
     * A form that as an underlying actor acting as a controller.
     *
     * @param actor The controller actor.
     */
    public ActorForm(ActorRef actor) {
        this.actor = actor;
        setSizeUndefined();
    }

    public ActorRef getActor() {
        return actor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActorForm)) {
            return false;
        }

        ActorForm that = (ActorForm) o;
        return Objects.equals(actor, that.actor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actor.path());
    }
}
