package graphs;

/**
 * Created by zua on 31.08.16.
 */
public class Neo4jQueryFactory {

    // SINGLETON FACTORY DEFINITION
    private static final Neo4jQueryFactory instance = new Neo4jQueryFactory();

    private Neo4jQueryFactory() {
    }

    public static Neo4jQueryFactory getInstance() {
        return instance;
    }

    public String findUserByEmailQuery(String email) {
        String match = "MATCH (u : User) ";
        String where = "WHERE u.email=" + "'" + email + "' ";
        return match + where + "RETURN u";
    }

    public String findRegisterByEmailQuery(String username) {
            String match = "MATCH (u : User) -[r : register]-> (a : Account) ";
            String where = "WHERE u.email=" + "'" + username + "' ";
            return match + where + "RETURN r";
    }

    // FACTORY METHODS

}
