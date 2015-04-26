public class Solution {
    public int search(int[] A, int target) {
        int n = A.length;
        int L = 0, R = n-1;
        while (L < R) {
            int M = (L+R)/2;
            // lower half ascending
            if (A[L] <= A[M]) {
                if (A[L] <= target && target <= A[M]) {
                    // in lower half
                    R = M;
                } else {
                    // in upper half
                    L = M+1;
                }
            } else {    // upper half ascending
                if (A[M] <= target && target <= A[R]) {
                    L = M;
                } else {
                    R = M;
                }
            }
        }
        if (A[R] == target) return L;
        return -1;
    }
}