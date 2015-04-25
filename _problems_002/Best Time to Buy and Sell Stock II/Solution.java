public class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int n = prices.length;
        if (n <= 1) return 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }
}