public class Solution {
    public int findMin(int[] A) {
       int L = 0, R = A.length - 1;
       while (L < R && A[L] >= A[R]) {
          int M = (L + R) / 2;
          if (A[M] > A[R]) {
             L = M + 1;
          } else {
             R = M;
          }
       }
       return A[L];
    }
}
