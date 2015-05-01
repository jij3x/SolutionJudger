public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) return 0;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m+1][n+1];
        dp[m-1][n] = 1;
        for (int r = m-1; r >= 0; r--) {
            for (int c = n-1; c >= 0; c--) {
                dp[r][c] = (obstacleGrid[r][c] == 1) ? 
                    0 :
                    dp[r][c+1] + dp[r+1][c];
            }
        }
        return dp[0][0];
    }
}