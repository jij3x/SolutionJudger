public class Solution {
    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    public void wiggleSort(int[] A) {
        boolean noless = true;
        for (int i = 1; i < A.length; i++) {
            if (noless) {
                if (A[i] < A[i - 1]) {
                    swap(A, i, i - 1);
                }
            } else {
                if (A[i] > A[i - 1]) {
                    swap(A, i, i - 1);
                }
            }
            noless = !noless;
        }
    }
}