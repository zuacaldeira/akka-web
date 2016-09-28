package views.components;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by zua on 01.09.16.
 */
public class ActorFormTest extends UITest {
    @Test(dataProvider = "equals")
    public void testEquals(ActorForm a, ActorForm b) throws Exception {
        assertTrue(a.equals(b));
    }

    @Test(dataProvider = "equals")
    public void testHashCode(ActorForm a, ActorForm b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test(dataProvider = "inequals")
    public void testInequals(ActorForm a, ActorForm b) throws Exception {
        assertFalse(a.equals(b));
    }


    @DataProvider(name = "equals")
    public Object[][] equals() {
        ActorForm af1 = new ActorForm() {
            @Override
            public void validate(Object value) throws InvalidValueException {

            }
        };

        return new Object[][]{
                {af1, af1}
        };
    }

    @DataProvider(name = "inequals")
    public Object[][] inequals() {
        ActorForm form1 = new ActorForm() {@Override public void validate(Object value) throws InvalidValueException {}};
        ActorForm form2 = new ActorForm() {@Override public void validate(Object value) throws InvalidValueException {}};

        return new Object[][]{
                {form1, form2},
                {form1, null}
        };
    }
}
