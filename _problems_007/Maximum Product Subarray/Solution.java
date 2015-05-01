public class Solution {
    public int maxProduct(int[] A) {
        int n = A.length;
        if (n == 0) return 0;
        int maxP = A[0], minP = A[0], maxAns = A[0];
        for (int i = 1; i < n; i++) {
            int mx = maxP, mn = minP;
            maxP = Math.max(Math.max(A[i], mx * A[i]), mn * A[i]);
            minP = Math.min(Math.min(A[i], mx * A[i]), mn * A[i]);
            maxAns = Math.max(maxP, maxAns);
        }
        return maxAns;
    }
}