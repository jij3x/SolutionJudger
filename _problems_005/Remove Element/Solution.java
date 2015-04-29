public class Solution {
    public int removeElement(int[] A, int elem) {
        int n = A.length;
        int i = 0, j = n - 1;
        while (i <= j) {
            if (A[i] == elem) {
                if (A[j] == elem) {
                    j--;
                } else {
                    swap(A, i, j);
                    i++;
                    j--;
                }
            } else {
                i++;
            }
        }
        return i;
    }
    void swap(int[] a, int i, int j) {
      int t = a[i];
      a[i] = a[j];
      a[j] = t;
   }
}