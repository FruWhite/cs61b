import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String[] Palindromes = new String[]{null, "a", "A", "abcba", "AbaBa"};
        String[] notPalindromes = new String[]{"ab", "aaaaab"};
        for (String word : Palindromes) {
            assertTrue(palindrome.isPalindrome(word));
        }
        for (String word : notPalindromes) {
            assertFalse(palindrome.isPalindrome(word));
        }
    }

    @Test
    public void testOverloadedIsPalindrome() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", cc));

        assertTrue(palindrome.isPalindrome("&%", cc));
    }
}
