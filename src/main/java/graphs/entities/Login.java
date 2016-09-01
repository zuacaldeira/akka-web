package graphs.entities;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.Objects;

/**
 * Login relationship entity represents a relation between a user
 * and an account, and can be expressed as login(user, account).
 * This relationship reads 'user u is loged in with account a'.
 *
 * Created by zua on 30.08.16.
 */
@RelationshipEntity(type = "login")
public class Login extends Entity {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Login)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Login login = (Login) o;
        return Objects.equals(user, login.user) &&
                Objects.equals(account, login.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, account);
    }
}
