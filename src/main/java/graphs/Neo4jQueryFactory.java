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

    // FACTORY METHODS

}
