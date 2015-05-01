public class Solution {
    public boolean isNumber(String s) {
        if (s == null) return false;
        int i = 0;
        int n = s.length();
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            i++;
        }
        boolean hasDigit = false, hasDot = false, hasSpace = false, hasExp = false, hasExpSign = false;
        while (i < n) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                if (hasSpace) return false;
                hasDigit = true;
            } else if (c == '.') {
                if (hasSpace) return false;
                if (hasDot) return false;
                if (hasExp) return false;   // exponent must be an integer
                hasDot = true;
            } else if (c == ' ') {
                hasSpace = true;
            } else if (c == 'e') {
                if (!hasDigit) return false;
                if (hasExp) return false;
                hasExp = true;
                hasDigit = false;
            } else if (c == '+' || c == '-') {
                if (!hasExp) return false;
                if (hasDigit) return false;
                if (hasExpSign) return false;
                hasExpSign = true;
            } else {
                return false;
            }
            i++;
        }
        return hasDigit;
    }
}