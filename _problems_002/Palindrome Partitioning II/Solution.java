public class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];
        preprocess(s, isPalin);
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (isPalin[0][i-1]) {
                dp[i] = 0;
            } else {
                int cut = Integer.MAX_VALUE;
                for (int j = 1; j < i; j++) {
                    if (isPalin[j][i-1]) {
                        cut = Math.min(cut, dp[j] + 1);
                    }
                }
                dp[i] = cut;
            }
        }
        return dp[n];
    }
    void preprocess(String s, boolean[][] isPalin) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
          isPalin[i][i] = true;
        }
        for (int len = 2; len <= n; len++) {
          for (int i = 0; i <= n-len; i++) {
            isPalin[i][i+len-1] = (((i+1 > i+len-2) || isPalin[i+1][i+len-2]) && s.charAt(i) == s.charAt(i+len-1));
          }
        }
      }
  
}