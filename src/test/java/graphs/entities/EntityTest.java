package graphs.entities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 02.09.16.
 */
public class EntityTest {
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
            {a, b}, {b, c}, {a, c}
        };
    }

}