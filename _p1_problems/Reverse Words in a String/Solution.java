public class Solution {
    public void reverseWords(StringBuilder s) {
        StringBuilder reversed = new StringBuilder();
        int j = s.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                j = i;
            } else if (i == 0 || s.charAt(i - 1) == ' ') {
                if (reversed.length() != 0) {
                    reversed.append(' ');
                }
                reversed.append(s.substring(i, j));
            }
        }

        for (int i = 0; i < s.length(); i++) {
            s.setCharAt(i, reversed.charAt(i));
        }
        reversed.toString();
    }
}