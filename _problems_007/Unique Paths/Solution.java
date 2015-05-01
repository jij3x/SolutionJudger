public class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        dp[m-1][n] = 1;
        for (int r = m-1; r >= 0; r--) {
            for (int c = n-1; c >= 0; c--) {
                dp[r][c] = dp[r][c+1] + dp[r+1][c];
            }
        }
        return dp[0][0];
    }
}