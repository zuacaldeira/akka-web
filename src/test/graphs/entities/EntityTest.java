package graphs.entities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by zua on 02.09.16.
 */
public class EntityTest {

    @Test(dataProvider = "ids")
    public void testSetId(Entity entity) {
        Long expectedId = 0L;
        assertNotEquals(entity.getId(), expectedId);

        entity.setId(expectedId);
        assertEquals(entity.getId(), expectedId);
    }

    @Test(dataProvider = "equals")
    public void testEquals(Entity a, Entity b) throws Exception {
        assertTrue(a.equals(b));
    }

    @Test(dataProvider = "equals")
    public void testHashCode(Entity a, Entity b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @DataProvider(name = "equals")
    public Object[][] equals() {
        Entity a = new Entity() {};
        a.setId(1L);

        Entity b = new Entity() {};
        b.setId(1L);

        Entity c = new Entity() {};
        c.setId(1L);

        return new Object[][] {
            {a, b}, {b, c}, {a, c}, {a, a}
        };
    }

    @Test(dataProvider = "inequals")
    public void testInequals(Entity a, Object b) throws Exception {
        assertFalse(a.equals(b));
    }

    @DataProvider(name = "inequals")
    public Object[][] inequals() {
        Entity a = new Entity() {};
        a.setId(1L);

        Entity b = new Entity() {};
        b.setId(2L);

        Entity c = new Entity() {};
        c.setId(3L);

        return new Object[][] {
                {a, b}, {b, c}, {a, c}, {a, new User()}, {a, "Hello"}
        };
    }

    @DataProvider(name = "ids")
    public Object[][] ids() {
        Entity a = new Entity() {};
        a.setId(1L);

        Entity b = new Entity() {};
        b.setId(2L);

        Entity c = new Entity() {};
        c.setId(3L);

        return new Object[][] {
                {a}, {b}, {c}
        };
    }
}