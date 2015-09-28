public class Solution {
    public int numSquares(int n) {
        if (n == 0)
            return 0;

        int[] memo = new int[n + 1];
        return dp(n, memo);
    }

    private int dp(int n, int[] memo) {
        if (n == 0)
            return 0;

        if (memo[n] != 0)
            return memo[n];

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= Math.sqrt(n); i++) {
            min = Math.min(min, dp(n - i * i, memo) + 1);
        }
        memo[n] = min;
        return min;
    }
}
