public class Solution {
    public int searchInsert(int[] A, int target) {
        int n = A.length;
        int L = 0, R = n-1;
        while (L < R) {
            int M = (L+R)/2;
            if (target > A[M]) {
                L = M+1;
            } else {
                R = M;
            }
        }
        if (L == n-1 && target > A[L]) return n;
        return L;        
    }
}