public class Solution {
    public int removeDuplicates(int[] A) {
        if (A.length == 0) return 0;
        int i = 0;
        for (int j = 0; j < A.length; j++) {
            if (A[i] != A[j]) {
                i++;
                A[i] = A[j];
            }
        }
        return i + 1;
    }
}