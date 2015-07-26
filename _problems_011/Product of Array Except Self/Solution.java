public class Solution {
    public int[] productExceptSelf(int[] A) {
        int n = A.length;
        int[] output = new int[n];
        int left = 1;
        int right = 1;
        for (int i = 0; i < n; i++)
            output[i] = 1;
        for (int i = 0; i < n; i++) {
            output[i] *= left;
            output[n - 1 - i] *= right;
            left *= A[i];
            right *= A[n - 1 - i];
        }
        return output;
    }
}