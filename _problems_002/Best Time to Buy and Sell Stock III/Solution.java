public class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int[] maxL = getL(prices);
        int[] maxR = getR(prices);
        int profit = 0;
        for (int i = 0; i < n-1; i++) {
            profit = Math.max(profit, maxL[i] + maxR[i+1]);
        }
        profit = Math.max(profit, maxL[n-1]);
        return profit;
    }
    int[] getL(int[] prices) {
        int n = prices.length;
        int[] maxL = new int[n];
        int lo = prices[0];
        maxL[0] = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i-1]) {
                maxL[i] = Math.max(maxL[i-1], prices[i] - lo);
            } else {
                maxL[i] = maxL[i-1];
                lo = Math.min(lo, prices[i]);
            }
        }
        return maxL;
    }
    int[] getR(int[] prices) {
        int n = prices.length;
        int[] maxR = new int[n];
        int hi = prices[n-1];
        maxR[n-1] = 0;
        for (int i = n-2; i >= 0; i--) {
            if (prices[i] < prices[i+1]) {
                maxR[i] = Math.max(maxR[i+1], hi - prices[i]);
            } else {
                maxR[i] = maxR[i+1];
                hi = Math.max(hi, prices[i]);
            }
        }
        return maxR;
    }
}