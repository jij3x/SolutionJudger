public class Solution {
    private int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; ++i)
            if (prices[i] > prices[i-1]) res += prices[i] - prices[i-1];
        return res;
    }
    
    public int maxProfit(int k, int[] prices) {
        if (prices.length < 2 || k == 0) return 0;
        if (k >= prices.length / 2) return maxProfit(prices);
        // using DP to solve, time complexity is O(kn), space complexity is O(k)
        int i, j, sign = -1;
        int[] k_buysells = new int[k << 1];
        Arrays.fill(k_buysells, Integer.MIN_VALUE);
        int[] prev_k_buysells = new int[k << 1];
        for (i = 0; i < prices.length; ++i) {
            for (j = 0; j < k_buysells.length; ++j) prev_k_buysells[j] = k_buysells[j];
            for (j = 0, sign = -1; j < k_buysells.length && j <= i; ++j, sign *= -1)
                k_buysells[j] = Math.max(prev_k_buysells[j], sign * prices[i] + (j > 0 ? prev_k_buysells[j-1] : 0));
        }
        int res = 0;
        for (i = (k << 1) - 1; i >= 1; i -= 2) res = Math.max(res, k_buysells[i]);
        return res;
    }
}
