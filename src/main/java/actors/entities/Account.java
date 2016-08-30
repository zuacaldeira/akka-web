package actors.entities;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by zua on 30.08.16.
 */
@NodeEntity
public class Account extends Entity {
    private  String email;
    private  String password;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
