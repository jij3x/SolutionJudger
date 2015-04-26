public class Solution {
    public void sortColors(int[] A) {
        int i = 0, j = 0, k = A.length;
        while (j < k) {
            if (A[j] == 0) {
                swap(A, j, i);
                i++;
                j++;
            } else if (A[j] == 1) {
                j++;
            } else {
                swap(A, j, k-1);
                k--;
            }
        }
    }
    void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}