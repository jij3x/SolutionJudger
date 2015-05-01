public class Solution {
    public int rob(int[] A) {
        if (A.length == 0)
            return 0;
        int[] B = {0, 0, 0, 0};
        for(int i=0; i < A.length; i++){
            int score = A[i];
            B[3] = Math.max(Math.max(score, B[2]), Math.max(score + B[1], score + B[0]));
            B[0] = B[1];
            B[1] = B[2];
            B[2] = B[3];
            B[3] = 0;
        }
        return B[2];
    }
}