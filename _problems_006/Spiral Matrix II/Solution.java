public class Solution {
    int startVal;
    public int[][] generateMatrix(int n) {
        int[][] mat = new int[n][n];
        if (n == 0) return mat;
        int steps = n-1;
        int i = 0;
        startVal = 1;
        while (steps > 0) {
            fill(i,     i,      0,  1, steps, mat);
            fill(i,     n-i-1,  1,  0, steps, mat);
            fill(n-i-1, n-i-1,  0, -1, steps, mat);
            fill(n-i-1, i,     -1,  0, steps, mat);
            steps -= 2;
            i++;
        }
        if (steps == 0)
            mat[n/2][n/2] = n*n;
        return mat;
    }
    void fill(int row, int col, int dRow, int dCol, int steps, int[][] mat) {
        int s = 0;
        for (int r = row, c = col; s < steps; r += dRow, c += dCol, s++, startVal++) {
            mat[r][c] = startVal;
        }
    }
}