package views.factories;

import akka.actor.ActorRef;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

/**
 * Created by zua on 29.08.16.
 */
public abstract class ActorView extends VerticalLayout{
    private final ActorStatus status;
    public ActorView(ActorRef actor, ActorStatus status, List<String> messages) {
        this.status = status;
        setStyleName(status.name().trim().toLowerCase());

        setSizeUndefined();
        addComponent(new Label(actor.path().name()));
        messages.stream().forEach(m -> {
            MessageView mv = new MessageView(actor, m);
            addComponent(mv);
        });

        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    }
}
