public class Solution {
    private int least;

    public int numSquares(int n) {
        least = n;
        int[] tbl = new int[(int) Math.sqrt(n)];
        for (int i = 0; i < tbl.length; i++) {
            tbl[i] = (i + 1) * (i + 1);
        }

        for (int i = 0; i < tbl.length; i++)
            dfs(tbl, i, 0, n);
        return least;
    }

    private void dfs(int[] tbl, int start, int steps, int target) {
        if (target == 0) {
            least = Math.min(least, steps);
        } else if (start < tbl.length && target > 0) {
            for (int i = start, t = target; i < tbl.length; t -= tbl[i++]) {
                dfs(tbl, i, steps + 1, t - tbl[i]);
            }
        }
    }
}
