package graphs.entities.nodes;

import graphs.entities.Entity;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.Objects;

/**
 * LoginAs relationship entity represents a relation between a user
 * and an account, and can be expressed as login(user, account).
 * This relationship reads 'user u is loged in with account a'.
 *
 * Created by zua on 30.08.16.
 */
@RelationshipEntity(type = "LOGIN_AS")
public class LoginAs extends Entity {

    @StartNode
    private User user;

    @EndNode
    private Account account;

    /**
     * Creates a new login relationship between a user and it's account.
     *
     * @param user  The user who wants to login
     * @param account The account to log in
     */
    public LoginAs(User user, Account account) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoginAs)) {
            return false;
        }

        LoginAs loginAs = (LoginAs) o;
        return Objects.equals(user, loginAs.user) &&
                Objects.equals(account, loginAs.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, account);
    }
}
