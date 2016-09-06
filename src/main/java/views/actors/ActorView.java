package views.actors;

import actors.core.ActorSystemsNames;
import actors.messages.AkkaMessages;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vaadin.ui.*;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Base class for designing actors views, composed by a label and a button for each message the actor processes.
 *
 * Created by zua on 29.08.16.
 */
public abstract class ActorView extends VerticalLayout implements Button.ClickListener {
    private final ActorRef actorRef;
    private final List<String> messages;
    private HorizontalLayout mailboxes;
    private Label actorNameLabel;
    private transient Logger log;


    /**
     * Basic actor view.
     *
     * @param actor The underlying actor
     * @param messages The messages the actor is supposed to process
     */
    public ActorView(Class<?> actor, List<String> messages) {
        this.actorRef = createActorRef(actor);
        this.messages = messages;

        mailboxes = new HorizontalLayout();
        mailboxes.setSpacing(true);
        mailboxes.setSizeFull();
        mailboxes.setHeightUndefined();
        mailboxes.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);

        setWidth("33%");
        setHeight("100%");
        setMargin(true);
        setSpacing(true);
        log = Logger.getLogger(this.getClass().getSimpleName());

        addActorName(actor.getSimpleName());
        addContent();
        addMailboxes();

        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        setExpandRatio(actorNameLabel, .2f);
        setExpandRatio(mailboxes, .1f);
        setStyleName("actor");
    }

    protected ActorRef createActorRef(Class<?> actor) {
        return ActorSystem.create(ActorSystemsNames.ACTOR_SYSTEM_VIEW.getAlias())
                .actorOf(Props.create(actor), actor.getSimpleName());
    }

    protected abstract void addContent();

    private void addActorName(String name) {
        actorNameLabel = new Label(name);
        actorNameLabel.setSizeFull();
        addComponent(actorNameLabel);
        setComponentAlignment(actorNameLabel, Alignment.BOTTOM_CENTER);
        setStyleName(StyleClassNames.ACTOR_NAME);
    }

    private void addMailboxes() {
        messages.stream().forEach(m -> {
            MessageView mv = new MessageView(actorRef, m);
            mv.addClickListener(this);
            mv.setStyleName(StyleClassNames.MESSAGE);
            mv.setId(m);
            mailboxes.addComponent(mv);

        });
        addComponent(mailboxes);
    }

    protected void addCancelButton() {
        MessageView cancel = new MessageView(getActorRef(), AkkaMessages.CANCEL);
        cancel.addClickListener(this);
        cancel.setStyleName(StyleClassNames.MESSAGE);
        cancel.setId(AkkaMessages.CANCEL);
        mailboxes.addComponent(cancel, 0);

    }

    public ActorRef getActorRef() {
        return actorRef;
    }

    public List<String> getMessages() {
        return messages;
    }

    public HorizontalLayout getMailboxes() {
        return mailboxes;
    }

    public Logger getLog() {
        return log;
    }

    protected void logException(Exception ilx) throws Exception {
        String msg = ilx.getMessage();
        Notification.show(msg, Notification.Type.ERROR_MESSAGE);
        log.log(Level.SEVERE, msg);
    }

    protected void logException(IllegalArgumentException ilx) {
        String msg = ilx.getMessage();
        Notification.show(msg, Notification.Type.ERROR_MESSAGE);
        log.log(Level.SEVERE, msg);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActorView)) {
            return false;
        }

        ActorView that = (ActorView) o;
        return Objects.equals(actorRef.path(), that.actorRef.path()) &&
                Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorRef.path(), messages);
    }
}
