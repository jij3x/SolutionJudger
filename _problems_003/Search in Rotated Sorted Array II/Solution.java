public class Solution {
    public boolean search(int[] A, int target) {
        int L = 0, H = A.length-1;
        while (L < H) {
            int M = (L+H)/2;
            if (A[L] == A[M]) {
                if (A[L] == target || A[H] == target) {
                    return true;
                } else {
                    H--;
                }
            } else if (A[L] < A[M]) {
                if (A[L] <= target && target <= A[M]) {  // lower left is sorted
                    H = M;
                } else {   
                    L = M+1;
                }
            } else {    // upper right must be sorted
                if (A[M] <= target && target <= A[H]) {  // k is between M and H
                    L = M;
                } else {   
                    H = M-1;
                }
            }
        }
        return A[L] == target;
    }
}