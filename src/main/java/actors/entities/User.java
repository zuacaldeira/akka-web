package actors.entities;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by zua on 30.08.16.
 */
@NodeEntity
public class User extends Entity {
    private String email;
    private String fullname;

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
}
