public class Solution {
    public int[] searchRange(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int[] ret = new int[2];
        int n = A.length;
        int lo = searchLower(A, 0, n-1, target);
        if (lo == -1) {
            ret[0] = -1; ret[1] = -1;
            return ret;
        }
        int hi = searchUpper(A, lo, n-1, target);
        ret[0] = lo;
        ret[1] = hi;
        return ret;
    }
    int searchLower(int A[], int L, int R, int target) {
        while (L < R) {
            int M = (L+R)/2;
            if (target <= A[M]) {
                R = M;
            } else {
                L = M+1;
            }
        }
        if (A[L] == target) return L;
        return -1;
    }
    int searchUpper(int A[], int L, int R, int target) {
        while (L < R) {
            int M = (L+R+1)/2;
            if (target >= A[M]) {
                L = M;
            } else {
                R = M-1;
            }
        }
        if (A[L] == target) return L;
        return -1;
    }
}
