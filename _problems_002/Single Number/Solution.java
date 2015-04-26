public class Solution {
    public int singleNumber(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int n = A.length;
        int x = 0;
        for (int i = 0; i < n; i++)
            x = x ^ A[i];
        return x;
    }
}