public class Solution {
    int count, all;

    public int totalNQueens(int n) {
        count = 0;
        all = (1 << n) - 1;
        doTotalNQueens(0, 0, 0);
        return count;
    }

    private void doTotalNQueens(int ld, int row, int rd) {
        int poss, p;
        if (row == all) {
            count++;
        } else {
            poss = all & ~(ld | row | rd);
            while (poss != 0) {
                p = poss & (-poss); // get poss' right most '1'
                poss = poss - p;
                doTotalNQueens((ld | p) << 1, row | p, (rd | p) >> 1);
            }
        }
    }
}
