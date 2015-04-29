public class Solution {
    boolean isOpen(char c) {
        return c == '[' || c == '{' || c == '(';
    }
    char getMatchOpen(char c) {
        switch (c) {
            case ']':
                return '[';
            case '}':
                return '{';
            case ')':
                return '(';
        }
        return '\0';        
    }
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<Character>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (isOpen(s.charAt(i))) {
                st.push(s.charAt(i));
            } else {
                if (st.empty() || (Character)st.peek() != getMatchOpen(s.charAt(i))) {
                    return false;
                }
                st.pop();
            }
        }
        return st.empty();
    }
}
