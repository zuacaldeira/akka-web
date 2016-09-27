package actors.mvc.views;

import actors.messages.ControlMessage;
import com.vaadin.ui.*;
import views.ui.AkkaUI;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Base class for designing actors views, composed by a label and a button for each message the actor processes.
 *
 * Created by zua on 29.08.16.
 */
public abstract class ActorView extends VerticalLayout implements Button.ClickListener {
    private transient Logger log;
    private Label actorNameLabel;
    private Component actorContent;
    private Mailboxes mailboxes;
    private Map<ControlMessage, Mailbox> mailboxMap;


    /**
     * Basic actor view.
     *
     */
    public ActorView() {
        log = Logger.getLogger(this.getClass().getSimpleName());
        setMargin(true);
        setSpacing(true);
        setSizeFull();
        initBase();
        align();
        setStyleName(StyleClassNames.ACTOR.getStyle());
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
        initActorName("");
        actorContent = createActorContent();
        initMailboxes();
        addComponents(actorNameLabel, actorContent, mailboxes);

    }

    private void initActorName(String name) {
        actorNameLabel = new Label(name);
        actorNameLabel.setSizeUndefined();
        actorNameLabel.setStyleName(StyleClassNames.ACTOR_NAME.getStyle());
    }

    protected abstract Component createActorContent();

    public void setActorName(String name) {
        actorNameLabel.setValue(name);
    }

    private Mailboxes initMailboxes() {
        mailboxMap = new HashMap<>();
        mailboxes = new Mailboxes();
        mailboxes.setSizeUndefined();
        mailboxes.setSpacing(true);
        return mailboxes;
    }

    protected void addMailbox(ControlMessage message, boolean enabled) {
        Mailbox mailbox = createMessage(message.name(), enabled);
        mailboxMap.put(message, mailbox);
        getMailboxes().addComponent(mailbox);
    }

    protected Mailbox createMessage(String message, boolean enabled) {
        Mailbox mv = new Mailbox(message);
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
        addMailbox(ControlMessage.CANCELLED, true);
        getMailbox(ControlMessage.CANCELLED).addClickListener(this);
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

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(31);
    }


    public AkkaUI getUI() {
        return (AkkaUI) super.getUI();
    }

    public Mailbox getMailbox(ControlMessage message) {
        return mailboxMap.get(message);
    }

    public Mailbox getMessage(ControlMessage message) {
        return getMailbox(message);
    }

}
