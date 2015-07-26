public class Solution {
    public boolean searchMatrix(int[][] mat, int target) {
        int M = mat.length, N = mat[0].length;
        int row = 0, col = N-1;
        while (row <= M-1 && col >= 0) {
            if (mat[row][col] < target) 
                row++;
            else if (mat[row][col] > target)
                col--;
            else
                return true;
        }
        return false;
    }
}