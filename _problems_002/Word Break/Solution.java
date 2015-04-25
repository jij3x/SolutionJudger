public class Solution {
    // DP Solution Top Down.
    public boolean wordBreak(String s, Set<String> dict) {
        int n = s.length();
        boolean [] F = new boolean[n];
        for (int i = 0; i < n; i++) {
          String t = "";
            for (int k = i; k >= 0; k--) {
                t = s.charAt(k) + t;
                if ((k == 0 || F[k - 1]) && dict.contains(t)) {
                    F[i] = true;
                    break;
                }
            }
        }
        return F[n - 1];
    }
}
