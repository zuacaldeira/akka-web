package views.actors;

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
    private final ActorRef actorRef;
    private final List<String> messages;
    private VerticalLayout mailboxes;

    public ActorView(Class<?> actor, List<String> messages) {
        this.actorRef = ActorSystem.create("ViewActorSystem").actorOf(Props.create(actor));
        this.messages = messages;

        setSizeUndefined();
        setSpacing(true);

        addComponent(new Label(actor.getSimpleName()));
        addMailboxes();

        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    }

    private void addMailboxes() {
        mailboxes = new VerticalLayout();
        mailboxes.setMargin(true);
        mailboxes.setSpacing(true);

        messages.stream().forEach(m -> {
            MessageView mv = new MessageView(actorRef, m);
            mailboxes.addComponent(mv);
        });

        addComponent(mailboxes);
    }

    public ActorRef getActorRef() {
        return actorRef;
    }

    public List<String> getMessages() {
        return messages;
    }

    public VerticalLayout getMailboxes() {
        return mailboxes;
    }
}
