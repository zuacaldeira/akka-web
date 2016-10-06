package actors.mvc;

import actors.business.RegistrationValidator;
import actors.exceptions.UserUniquenessViolation;
import actors.messages.ControlMessage;
import actors.messages.RegisterMessage;
import graphs.Neo4jQueryFactory;
import graphs.Neo4jSessionFactory;
import graphs.entities.nodes.Account;
import graphs.entities.nodes.RegisteredAs;
import graphs.entities.nodes.User;
import org.neo4j.ogm.session.Session;
import views.ui.AkkaUI;

import java.util.Collections;

/**
 * Created by zua on 28.08.16.
 */
public class RegisterActor extends MVCActor {

    protected RegisterActor(AkkaUI ui, User user) {
        super(ui, user);
    }

    @Override
    public void onReceive(Object message) {
        if( message instanceof RegisterMessage) {
            if(valid((RegisterMessage) message)) {
                register((RegisterMessage) message);
            }
        }
        else {
            super.onReceive(message);
        }
    }

    private boolean valid(RegisterMessage message) {
        return new RegistrationValidator().isValid(message);
    }

    private void register(RegisterMessage message) {
        //  Create session
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        // query the graph for a matching user
        User u = session.queryForObject(User.class, Neo4jQueryFactory.getInstance().findUserByEmailQuery(message.getEmail()), Collections.EMPTY_MAP);
        // If we found one, then this registeredAs is invalid because it will lead to duplicated registrations
        if(u != null) {
            leaveAkkariaUIOnBusinessViolation(new UserUniquenessViolation(message.getEmail()));
        }

        // Otherwise, store a new registeredAs in the graph
        RegisteredAs registeredAs = new RegisteredAs(new User(message.getEmail(), message.getFullname()), new Account(message.getEmail(), message.getPassword()));
        session.save(registeredAs);
        // Return a confirmation message to the caller
        log.info("Stored registeredAs for user [{}]", message.getEmail());
        leaveAkkariaOnSuccess(registeredAs.getUser());
    }

    @Override
    protected void leaveAkkariaOnSuccess(Object o) {
        getSender().tell(ControlMessage.SUCCESS, getSelf());
        if(getUi() != null) {
            getUi().leave(getSelf());
        }
        getContext().actorFor(getSelf().path().parent()).tell(ControlMessage.LOGIN, getSelf());
    }



}
