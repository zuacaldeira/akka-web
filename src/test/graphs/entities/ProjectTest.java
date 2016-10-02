package graphs.entities;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 02.10.16.
 */
public class ProjectTest {

    @Test
    public void testCreateProject() {
        String title = "PTitle";
        String description = "PDescription";
        Project p = new Project(title, description);
        assertTrue((p instanceof AliveEntity));
        assertEquals(p.getTitle(), title);
        assertEquals(p.getDescription(), description);
        assertTrue(p.getSubprojects().isEmpty());
    }

    @Test
    public void testAddSubprojectProject() {
        String title = "PTitle";
        String description = "PDescription";
        Project p = new Project(title, description);
        assertTrue((p instanceof AliveEntity));
        assertEquals(p.getTitle(), title);
        assertEquals(p.getDescription(), description);
        assertTrue(p.getSubprojects().isEmpty());
        p.addSubProject(new Project("P2", "D2"));
        assertFalse(p.getSubprojects().isEmpty());
    }

    @Test
    public void testSetParent() {
        String title = "PTitle";
        String description = "PDescription";
        Project p = new Project(title, description);
        assertTrue((p instanceof AliveEntity));
        assertEquals(p.getTitle(), title);
        assertEquals(p.getDescription(), description);
        assertTrue(p.getSubprojects().isEmpty());
        Project p2 = new Project("P2", "D2");
        p.addSubProject(p2);
        assertFalse(p.getSubprojects().isEmpty());
        assertEquals(p2.getParent(), p);
    }

}