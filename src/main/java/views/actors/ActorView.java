package views.actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.List;
import java.util.Objects;

/**
 * Base class for designing actors views, composed by a label and a button for each message the actor processes.
 *
 * Created by zua on 29.08.16.
 */
public abstract class ActorView extends VerticalLayout{
    private final ActorRef actorRef;
    private final List<String> messages;
    private VerticalLayout mailboxes;

    /**
     * Basic actor view.
     *
     * @param actor The underlying actor
     * @param messages The messages the actor is supposed to process
     */
    public ActorView(Class<?> actor, List<String> messages) {
        this.actorRef = ActorSystem.create(ActorSystems.ACTOR_SYSTEM_VIEW).actorOf(Props.create(actor));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActorView)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ActorView that = (ActorView) o;
        return Objects.equals(actorRef, that.actorRef) &&
                Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), actorRef, messages);
    }
}
