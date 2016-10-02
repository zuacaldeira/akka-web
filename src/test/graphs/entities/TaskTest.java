package graphs.entities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by zua on 01.10.16.
 */
public class TaskTest {


    @Test(dataProvider = "titleAndDescriptions")
    public void testCreateTask(String title, String description) {
        Task t = new Task(title, description);
        assertTrue(t instanceof AliveEntity);
        assertEquals(t.getTitle(), title);
        assertEquals(t.getDescription(), description);
    }

    @Test(dataProvider = "subTasks")
    public void testAddSubTask(String title, String description, Task subtask) {
        Task t = new Task(title, description);
        assertEquals(t.getTitle(), title);
        assertEquals(t.getDescription(), description);
        assertFalse(t.contains(subtask));
        t.addSubTask(subtask);
        assertTrue(t.contains(subtask));
    }

    @Test(dataProvider = "titleAndDescriptions")
    public void testLifefycle(String title, String description) {
        Task t = new Task(title, description);

        assertEquals(t.getTitle(), title);
        assertEquals(t.getDescription(), description);
        assertTrue(t.created());

        assertFalse(t.started());
        t.start();
        assertTrue(t.started());

        assertFalse(t.paused());
        t.pause();
        assertTrue(t.paused());

        assertFalse(t.resumed());
        t.resume();
        assertTrue(t.resumed());

        assertFalse(t.finished());
        t.finish();
        assertTrue(t.finished());
    }

    @Test(dataProvider = "invalidTitleAndDescriptions", expectedExceptions = IllegalArgumentException.class)
    public void createTaskInvalid(String title, String description) {
        Task t = new Task(title, description);
    }

    @DataProvider(name = "titleAndDescriptions")
    public Object[][] titleAndDescription() {
        return new Object[][] {
                {"title", "description"}
        };
    }

    @DataProvider(name = "invalidTitleAndDescriptions")
    public Object[][] invalidTitleAndDescription() {
        return new Object[][] {
                {null, "description"},
                {"title", null},
                {null, null},
                {"", ""}
        };
    }

    @DataProvider(name = "subTasks")
    public Object[][] subTasks() {
        return new Object[][] {
                {"title", "description", new Task("sub-title", "sub-description")}
        };
    }


}