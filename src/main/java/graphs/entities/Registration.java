package graphs.entities;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.Objects;

/**
 * Created by zua on 30.08.16.
 */
@RelationshipEntity(type = "register")
public class Registration extends Entity {

    @StartNode
    private User user;

    @EndNode
    private Account account;

    public Registration() {
        // Used by neo4j
    }

    /**
     * Creates a new Registration relationship between a user and an account.
     *
     * @param user The user
     * @param account The account
     */
    public Registration(User user, Account account) {
        this.user = user;
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Registration)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Registration that = (Registration) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, account);
    }
}
