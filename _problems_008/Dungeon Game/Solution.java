public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int nrow = dungeon.length;
        int ncol = dungeon[0].length;
        int[] dp = new int[ncol+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[ncol-1] = 1;
        
        for (int irow = nrow-1; irow >= 0; irow --)
        {
            for (int icol = ncol-1; icol >= 0; icol --)
            {
                int min_hp_required = Math.min(dp[icol], dp[icol+1]);
                dp[icol] = Math.max(min_hp_required - dungeon[irow][icol], 1);
            }
        }
        return dp[0];
    }
};