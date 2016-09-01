package graphs.entities;

import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

/**
 * Account entity node.
 * Defines a user account node in the graph database.
 *
 * Created by zua on 30.08.16.
 */
@NodeEntity
public class Account extends Entity {
    private  String email;
    private  String password;

    /**
     * Empty default construtor needed for neo4j to create instances of this class with data
     * from the database.
     */
    public Account() {
        // Used by neo4j to create a new instance
    }

    /**
     * Creates a new account entity object with credentials email and a password initialized.
     *
     * @param email The account's email
     * @param password  The account's password
     */
    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)){
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(email, account.email) &&
                Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, password);
    }
}
