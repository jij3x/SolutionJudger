public class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        for (int c = 1; c < n; c++) {
            grid[0][c] += grid[0][c-1];
        }
        for (int r = 1; r < m; r++) {
            grid[r][0] += grid[r-1][0];
        }
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                grid[r][c] = Math.min(grid[r][c-1], grid[r-1][c]) + grid[r][c];
            }
        }
        return grid[m-1][n-1];
    }
}