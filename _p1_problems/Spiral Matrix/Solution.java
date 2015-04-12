public class Solution {
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if (m == 0) return ret;
        int n = matrix[0].length;
        int lastRow = m-1, lastCol = n-1;
        int r, c;
        for (r = 0, c = 0; m > 1 && n > 1; r++, c++, m -= 2, n -= 2) {
            getOrder(matrix, r,         c,          0,  1, n-1, ret);
            getOrder(matrix, r,         lastCol-c,  1,  0, m-1, ret);
            getOrder(matrix, lastRow-r, lastCol-c,  0, -1, n-1, ret);
            getOrder(matrix, lastRow-r, c,         -1,  0, m-1, ret);
        }
        if (m == 1 && n == 1) {
            ret.add(matrix[r][c]);
        } else if (m == 1) {
            getOrder(matrix, r, c, 0, 1, n, ret);
        } else if (n == 1) {
            getOrder(matrix, r, c, 1, 0, m, ret);
        }
        return ret;
    }
    
    void getOrder(int[][] matrix, int row, int col, int dRow, int dCol, int steps, ArrayList<Integer> ret) {
        for (int i = 0, r = row, c = col; i < steps; i++, r += dRow, c += dCol) {
            ret.add(matrix[r][c]);
        }
    }
}
