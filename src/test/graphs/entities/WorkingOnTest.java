package graphs.entities;

import actors.Neo4JDatabaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by zua on 02.10.16.
 */
public class WorkingOnTest extends Neo4JDatabaseTest {


    @Test(dataProvider = "createData")
    public void createRelationshipTest(User u, Project p) {
        WorkingOn relationship = new WorkingOn(u, p);
        assertEquals(relationship.getUser(), u);
        assertEquals(relationship.getStartNode(), u);
        assertEquals(relationship.getProject(), p);
        assertEquals(relationship.getEndNode(), p);
    }


    @DataProvider(name = "createData")
    public Object[][] createData() {
        return new Object[][] {
                {new User(), new Project("P1", "D1")}
        };
    }
}
