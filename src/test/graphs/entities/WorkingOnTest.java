package graphs.entities;

import actors.Neo4JDatabaseTest;
import org.testng.annotations.Test;

/**
 * Created by zua on 02.10.16.
 */
public class WorkingOnTest extends Neo4JDatabaseTest {


    @Test(dataProvider = "createData")
    public void createRelationshipTest(User u, Project p) {
        WorkingOn relationship = new WorkingOn(u, p);
    }
}
