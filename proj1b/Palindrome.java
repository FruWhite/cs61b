public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new LinkedListDeque<>();
        if (word == null) {
            return dq;
        }
        for (int i = 0; i < word.length(); i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    public boolean isPalindrome(String word) {
        return isPalindromeHelper(wordToDeque(word));
    }
    private boolean isPalindromeHelper(Deque<Character> dq) {
        if (dq.isEmpty() || dq.size() == 1) {
            return true;
        }
        else if (Character.toLowerCase(dq.removeFirst()) == Character.toLowerCase(dq.removeLast())) {
            return isPalindromeHelper(dq);
        }
        else {return false;}

    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeHelper(wordToDeque(word), cc);
    }

    private boolean isPalindromeHelper(Deque<Character> dq, CharacterComparator cc) {
        if (dq.isEmpty() || dq.size() == 1) {
            return true;

        } else if (cc.equalChars(dq.removeFirst(), dq.removeLast())) {
            return isPalindromeHelper(dq, cc);
        } else {
            return false;
        }
    }
}
