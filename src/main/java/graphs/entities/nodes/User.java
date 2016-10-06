package graphs.entities.nodes;

import graphs.entities.Entity;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

/**
 * Created by zua on 30.08.16.
 */
@NodeEntity
public class User extends Entity {
    private String email;
    private String fullname;

    public User() {
        // Used by neo4j
    }

    /**
     * Creates a new user, given an email and it's full name.
     * @param email The user's email
     * @param fullname  The user's full name
     */
    public User(String email, String fullname) {
        this.email = email;
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return super.equals(this) && Objects.equals(email, user.email) &&
                Objects.equals(fullname, user.fullname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, fullname);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
