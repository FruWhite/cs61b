import static org.junit.Assert.*;

import org.junit.Test;


public class FlikTest {
    @Test
    public void testIsSameNumber() {
        assertTrue(Flik.isSameNumber(0, 0));
        int a = 500;
        assertTrue(Flik.isSameNumber(a, 500));
        assertTrue(Flik.isSameNumber(a, a));
        int b = 400;
        assertFalse(Flik.isSameNumber(a, b));
    }

}
