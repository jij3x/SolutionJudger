public class Solution {
    public int numSquares(int n) {
        int[] memo = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            memo[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= Math.sqrt(i); j++) {
                memo[i] = Math.min(memo[i], memo[i - j * j] + 1);
            }
        }
        return memo[n];
    }
}
