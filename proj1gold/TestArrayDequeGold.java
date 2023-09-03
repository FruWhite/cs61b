import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestArrayDequeGold {
    @Test
    public void testDeque() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        assertTrue("sad1.isEmpty()", sad1.isEmpty());

        int tmp = 0;
        int times = 25;
        String failureSeq = "";
        for (int i = 0; i < times; i++) {
            tmp = StdRandom.uniform(100);
            sad1.addFirst(tmp);
            ads1.addFirst(tmp);
            failureSeq += "addFirst(" + tmp + ")\n";
            assertEquals(failureSeq, ads1.get(0), sad1.get(0));
        }
        for (int i = 0; i < times / 2; i++) {
            failureSeq += "removeLast()\n";
            assertEquals(failureSeq, ads1.removeLast(), sad1.removeLast());
        }
        for (int i = 0; i < times; i++) {
            tmp = StdRandom.uniform(100);
            sad1.addLast(tmp);
            ads1.addLast(tmp);
            failureSeq += "addLast(" + tmp + ")\n";
            assertEquals(failureSeq, ads1.get(0), sad1.get(0));
        }
        for (int i = 0; i < times / 2; i++) {
            failureSeq += "removeFirst()\n";
            assertEquals(failureSeq, ads1.removeFirst(), sad1.removeFirst());
        }
        for (int i = 0; i < times; i++) {
            tmp = StdRandom.uniform(100);
            sad1.addFirst(tmp);
            ads1.addFirst(tmp);
            failureSeq += "addFirst(" + tmp + ")\n";
            assertEquals(failureSeq, ads1.get(0), sad1.get(0));
        }
        for (int i = 0; i < times; i++) {
            failureSeq += "removeFirst()\n";
            assertEquals(failureSeq, ads1.removeFirst(), sad1.removeFirst());
        }
        for (int i = 0; i < times / 2; i++) {
            failureSeq += "removeLast()\n";
            assertEquals(failureSeq, ads1.removeLast(), sad1.removeLast());
        }

    }
}
