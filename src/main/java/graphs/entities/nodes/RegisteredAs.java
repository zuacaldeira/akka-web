package graphs.entities.nodes;

import graphs.entities.Entity;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.Objects;

/**
 * Created by zua on 30.08.16.
 */
@RelationshipEntity(type = "REGISTERED_AS")
public class RegisteredAs extends Entity {

    @StartNode
    private User user;

    @EndNode
    private Account account;

    public RegisteredAs() {
        // Used by neo4j
    }

    /**
     * Creates a new RegisteredAs relationship between a user and an account.
     *
     * @param user The user
     * @param account The account
     */
    public RegisteredAs(User user, Account account) {
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
        if (!(o instanceof RegisteredAs)) {
            return false;
        }
        RegisteredAs that = (RegisteredAs) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, account);
    }
}
