package views.components;

/**
 * Creates a form that has a relationship with an actor.
 *
 * Created by zua on 29.08.16.
 */
public abstract class ActorForm extends MyForm {
    /**
     * A form that as an underlying actor acting as a controller.
     *
     */
    public ActorForm() {
        setSizeUndefined();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActorForm)) {
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
