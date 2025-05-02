public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (char c : word.toCharArray()) {
            deque.addLast(c);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        char[] charArray = word.toLowerCase().toCharArray();
        return _isPalindrome(charArray, 0);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        char[] charArray = word.toLowerCase().toCharArray();
        int stop;
        if (word.length() % 2 == 0) {
            stop = word.length() / 2;
        } else {
            stop = (word.length() - 1) / 2;
        }
        for (int i = 0; i < stop; i++) {
            if (!cc.equalChars(charArray[i], charArray[charArray.length - 1 - i])) {
                return false;
            }
        }
        return true;
    }

    private boolean _isPalindrome(char[] word, int i) {
        if (i >= word.length / 2) {
            return true;
        }
        if (word.length == 0 || word.length == 1) {
            return true;
        }
        if (word[i] != word[word.length-1-i]) {
            return false;
        }
        return _isPalindrome(word, i+1);
    }
}
