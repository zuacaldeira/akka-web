package views.actors;

import actors.business.ActorSystems;
import actors.messages.AkkaMessage;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vaadin.ui.*;
import views.components.AkkaUI;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Base class for designing actors views, composed by a label and a button for each message the actor processes.
 *
 * Created by zua on 29.08.16.
 */
public abstract class ActorView extends VerticalLayout implements Button.ClickListener {
    private final ActorRef actorRef;
    private transient Logger log;
    private Label actorNameLabel;
    private Mailboxes mailboxes;
    private Component actorContent;


    /**
     * Basic actor view.
     *
     * @param actor The underlying actor
     */
    public ActorView(Class<?> actor) {
        this.actorRef = createActorRef(actor);
        log = Logger.getLogger(this.getClass().getSimpleName());
        setMargin(true);
        setSpacing(true);
        setSizeFull();
        setWidth("50%");
        initBase();
        align();
    }

    private void align() {
        setComponentAlignment(actorNameLabel, Alignment.BOTTOM_CENTER);
        setComponentAlignment(actorContent, Alignment.MIDDLE_CENTER);
        setComponentAlignment(mailboxes, Alignment.TOP_CENTER);
    }

    private void initBase() {
        initBaseComponents();
        initExpandRatios();
    }

    private void initExpandRatios() {
        setExpandRatio(actorNameLabel, .4f);
        setExpandRatio(actorContent, .5f);
        setExpandRatio(mailboxes, .1f);
    }

    private void initBaseComponents() {
        actorNameLabel = getActorName(actorRef.path().name());
        actorContent = createActorContent();
        mailboxes = createMailboxes();
        addComponents(actorNameLabel, actorContent, mailboxes);
    }

    protected abstract Component createActorContent();

    protected ActorRef createActorRef(Class<?> actor) {
        return ActorSystem.create(ActorSystems.ACTOR_SYSTEM.getAlias())
                .actorOf(Props.create(actor), actor.getSimpleName());
    }

    private Label getActorName(String name) {
        actorNameLabel = new Label(name);
        actorNameLabel.setSizeUndefined();
        actorNameLabel.setStyleName(StyleClassNames.ACTOR_NAME.getStyle());
        return  actorNameLabel;
    }

    private Mailboxes createMailboxes() {
        mailboxes = new Mailboxes();
        mailboxes.setSizeUndefined();
        mailboxes.setSpacing(true);
        return mailboxes;
    }

    protected void addMessage(String message, boolean enabled) {
        getMailboxes().addComponent(createMessage(message, enabled));
    }

    public Mailbox getMessage(String message) {
        int n = getMailboxes().getComponentCount();
        for(int i = 0; i < n; i++) {
            Mailbox messageView = (Mailbox) getMailboxes().getComponent(i);
            if(messageView.getMessage().equals(message)) {
                return (Mailbox) getMailboxes().getComponent(i);
            }
        }
        return null;
    }

    protected Mailbox createMessage(String message, boolean enabled) {
        Mailbox mv = new Mailbox(actorRef, message);
        mv.addClickListener(this);
        mv.addStyleName(StyleClassNames.MESSAGE.getStyle());
        mv.setId(message);
        mv.setEnabled(enabled);
        if(enabled) {
            mv.addStyleName(StyleClassNames.ENABLED.getStyle());
        }
        return mv;
    }

    protected void addCancelButton() {
        Mailbox cancel = createMessage(AkkaMessage.CANCEL.name(), true);
        getMailboxes().addComponent(cancel, 0);
        cancel.addClickListener(this);
    }

    public ActorRef getActorRef() {
        return actorRef;
    }

    public Mailboxes getMailboxes() {
        return (Mailboxes) getComponent(getComponentCount()-1);
    }

    public Logger getLog() {
        return log;
    }

    public Component getActorContent() {
        return actorContent;
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
        return Objects.equals(actorRef.path(), that.actorRef.path());
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorRef.path());
    }


    public AkkaUI getUI() {
        return (AkkaUI) super.getUI();
    }
}
