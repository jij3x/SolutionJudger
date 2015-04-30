public class Solution {
    public int trap(int[] A) {
        if (A.length <= 2)
            return 0;

        int[] leftPeak = new int[A.length], rightPeak = new int[A.length];
        leftPeak[0] = A[0];
        rightPeak[A.length - 1] = A[A.length - 1];
        for (int i = 1, j = A.length - 2; i < A.length - 1; i++, j--) {
            leftPeak[i] = Math.max(leftPeak[i - 1], A[i]);
            rightPeak[j] = Math.max(rightPeak[j + 1], A[j]);
        }

        int sum = 0;
        for (int i = 1; i < A.length - 1; i++) {
            sum += Math.min(leftPeak[i], rightPeak[i]) - A[i];
        }
        return sum;
    }
}