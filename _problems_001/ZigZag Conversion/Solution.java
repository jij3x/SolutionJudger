public class Solution {
    public String convert(String s, int nRows) {
        if (nRows <= 1) return s;   // Special case
        int len = s.length();
        int nPerCol = 2 * (nRows - 1);
        int nCols = (len % nPerCol == 0) ? (len / nPerCol) : (len / nPerCol + 1);
        
        String ret = "";
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                int k = row + col * nPerCol;
                if (k < len) ret += s.charAt(k);
                if (row > 0 && row < nRows-1) {
                    int m = (col + 1) * nPerCol - row;
                    if (m < len) ret += s.charAt(m);
                }
            }
        }
        return ret;
    }
}