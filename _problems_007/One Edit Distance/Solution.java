public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if (m > n) return isOneEditDistance(t, s);
        if (n - m > 1) return false;
        boolean modified = false;
        for (int i = 0, j = 0; i < m; i++, j++) {
            if (s.charAt(i) == t.charAt(j)) continue;
            if (modified) return false;
            if (m != n) i--;
            modified = true;
        }
        return (m == n) ? modified : true;
    }
}