public class Solution {
    public int maximalSquare(char[][] matrix) {
        int nr = matrix.length;
        if (nr == 0) return 0;
        int nc = matrix[0].length, res = 0;
        int[][] w = new int[nr + 1][];
        int[][] h = new int[nr + 1][];
        int[][] len = new int[nr + 1][];
        for (int i = 0; i <= nr; ++i) {
            w[i] = new int[nc + 1];
            h[i] = new int[nc + 1];
            len[i] = new int[nc + 1];
            
        }
        for (int i = nr - 1; i >= 0; --i) {
            for (int j = nc - 1; j >= 0; --j) {
                if (matrix[i][j] == '1') {
                    w[i][j] = w[i][j+1] + 1;
                    h[i][j] = h[i+1][j] + 1;
                    len[i][j] = Math.min(len[i+1][j+1] + 1, Math.min(w[i][j], h[i][j]));
                    res = Math.max(res, len[i][j] * len[i][j]);
                }
            }
        }
        return res;
    }
}