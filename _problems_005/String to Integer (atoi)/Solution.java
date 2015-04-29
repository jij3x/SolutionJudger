public class Solution {
    public int atoi(String str) {
        int sign = 1;
        int n = str.length();
        int i = 0;
        while (i < n && str.charAt(i) == ' ') {
            i++;
        }
        if (i < n && str.charAt(i) == '+') {
            i++;
        } else if (i < n && str.charAt(i) == '-') {
            sign = -1;
            i++;
        }
        long num = 0;
        boolean overflow = false;
        while (i < n && !overflow && isAlpha(str.charAt(i))) {
            int dig = str.charAt(i) - '0';
            num = num*10 + dig;
            if (sign == 1 && num > Integer.MAX_VALUE ||
                sign == -1 && -num < Integer.MIN_VALUE)
                overflow = true;
            i++;
        }
        if (sign == -1) {
            num = -num;
        }
        if (overflow) {
            return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            return (int)num;
        }
    }
    boolean isAlpha(char c) {
        return c >= '0' && c <= '9';
    }
}