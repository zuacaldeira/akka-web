package graphs.entities;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by zua on 30.08.16.
 */
@RelationshipEntity(type = "login")
public class Login extends Entity {

    @StartNode
    private User user;

    @EndNode
    private Account account;

    public Login(User user, Account account) {
        this.user = user;
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public Account getAccount() {
        return account;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
