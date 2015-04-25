public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
        int m = A.length, n = B.length;
        int[] C = new int[m+n];
        int i = 0, j = 0, k = 0;
        while (i < m || j < n) {
        int a = ((i < m) ? A[i] : Integer.MAX_VALUE);
        int b = ((j < n) ? B[j] : Integer.MAX_VALUE);
        if (a < b) {
            assert(i < m);
            C[k++] = A[i++];
        } else {
            assert(j < n);
            C[k++] = B[j++];
        }
        }
        double median = (((m+n)%2 == 1) ? C[(m+n)/2] : (C[(m+n)/2-1] + C[(m+n)/2]) / 2.0);
        return median;
    }
}