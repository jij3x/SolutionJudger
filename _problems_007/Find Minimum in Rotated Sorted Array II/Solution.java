public class Solution {
    public int findMin(int[] A) {
        int L = 0, R = A.length - 1;
        while (L < R) {
            int M = (L + R) / 2;
            if (A[M] > A[R]) {
                L = M + 1;
            } else if (A[M] < A[L]) {
                R = M;
            } else if (A[L] == A[M] && A[M] == A[R]) {
                L = L + 1;
            } else {
                // A[L] = A[M] < A[R] or
                // A[L] < A[M] = A[R] or
                // A[L] < A[M] < A[R]
                R = L;
            }
        }
        return A[L];
    }
}