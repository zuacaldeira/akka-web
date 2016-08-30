package views.factories;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

/**
 * Created by zua on 29.08.16.
 */
public abstract class ActorView extends VerticalLayout{
    private final ActorStatus status;
    private final ActorRef actorRef;

    public ActorView(Class<?> actor, ActorStatus status, List<String> messages) {
        this.status = status;
        this.actorRef = ActorSystem.create("ViewActorSystem").actorOf(Props.create(actor, actor.getSimpleName()));

        setStyleName(status.name().trim().toLowerCase());
        setSizeUndefined();
        setSpacing(true);


        addComponent(new Label(actor.getSimpleName()));
        VerticalLayout mailboxes = new VerticalLayout();

        messages.stream().forEach(m -> {
            MessageView mv = new MessageView(actorRef, m);
            mailboxes.addComponent(mv);
        });

        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    }
}
