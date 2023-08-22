import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ArrayDequeTest {
    @Disabled
    @Test
    public void isEmptyAddGetTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        assertTrue(ad1.isEmpty());

        ad1.addFirst(10);
        assertEquals(ad1.get(0), 10);
        ad1.addLast(11);
        assertEquals(ad1.get(1), 11);
        assertEquals(ad1.size(), 2);
        ad1.addLast(12);
        ad1.addLast(13);
        ad1.addLast(14);
        ad1.addLast(15);
        ad1.addLast(16);
        ad1.addLast(17);
        for (int i = 1; i < 100; i++) {
            ad1.addLast(0);
        }
        assertEquals(ad1.get(ad1.size() - 1), 0);
        assertEquals(ad1.get(3), 13);
    }

    @Test
    public void fillUpEmptyFillUpAgain() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        for (int i = 1; i < 16; i++) {
            ad1.addFirst(0);
        }
        for (int i = 1; i < 16; i++) {
            assertEquals(0, ad1.removeFirst());
        }
        for (int i = 1; i < 1000; i++) {
            ad1.addFirst(0);
        }
    }
}
