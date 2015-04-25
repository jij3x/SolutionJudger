public class Solution {
    public int maxProfit(int[] stocks) {
      int min = 0;
      int maxDiff = 0;
      for (int i = 0; i < stocks.length; i++) {
        if (stocks[i] < stocks[min])
          min = i;
        int diff = stocks[i] - stocks[min];
        if (diff > maxDiff) {
          maxDiff = diff;
        }
      }
      return maxDiff;
    }
}