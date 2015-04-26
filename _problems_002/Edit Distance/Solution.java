public class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for (int r = 0; r < m+1; r++) {
            dp[r][0] = r;
        }
        for (int c = 0; c < n+1; c++) {
            dp[0][c] = c;
        }
        for (int r = 1; r < m+1; r++) {
            for (int c = 1; c < n+1; c++) {
                if (word1.charAt(r-1) == word2.charAt(c-1)) {
                    dp[r][c] = dp[r-1][c-1];
                } else {
                    dp[r][c] = Math.min(dp[r-1][c-1] + 1,
                                Math.min(dp[r][c-1] + 1, dp[r-1][c] + 1));
                }
            }
        }
        return dp[m][n];
    }
}
