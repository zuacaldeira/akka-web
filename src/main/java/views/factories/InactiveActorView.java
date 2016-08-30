package views.factories;

import java.util.List;

/**
 * Created by zua on 28.08.16.
 */
public class InactiveActorView extends ActorView {
    public InactiveActorView(Class<?> actor, List<String> messages) {
        super(actor, ActorStatus.INACTIVE, messages);
    }
}
