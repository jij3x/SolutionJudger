public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] fwd = new int[256];
        boolean[] rev = new boolean[256];
        for (int i = 0; i < s.length(); i++) {
            int x = s.charAt(i), y = t.charAt(i);
            if (rev[y]) {
                if (fwd[x] != y) return false;
            } else {
                if (fwd[x] != 0) return false;
                fwd[x] = y;
                rev[y] = true;
            }
        }
        return true;
    }
}