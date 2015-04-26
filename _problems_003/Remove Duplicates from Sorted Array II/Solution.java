public class Solution {
    public int removeDuplicates(int[] A) {
        if (A.length == 0) return 0;
        int count = 0;
        int i = 0;
        for (int j = 0; j < A.length; j++) {
            if (A[i] != A[j]) {
                if (count >= 2) {
                    A[i+1] = A[i];
                    i++;
                }
                i++;
                A[i] = A[j];
                count = 1;
            } else {
                count++;
            }
        }
        if (count >= 2) {
            A[i+1] = A[i];
            return i+2;
        }
        return i+1;
    }
}