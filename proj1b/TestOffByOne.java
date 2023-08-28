//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'c'));
        assertTrue(offByOne.equalChars('c', 'b'));
        assertTrue(offByOne.equalChars('%', '&'));

        assertFalse(offByOne.equalChars('a' ,'a'));
        assertFalse(offByOne.equalChars('a' ,'c'));
        assertFalse(offByOne.equalChars('a' ,'z'));
    }

}
