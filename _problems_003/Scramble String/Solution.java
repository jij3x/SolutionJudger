public class Solution {
    // Recursive DP + Cache (memoization).
    int[][][] dp = new int[64][64][64];
    public boolean isScramble(String s1, String s2, int pos1, int pos2, int length) {
        if (dp[pos1][pos2][length] != -1) return (dp[pos1][pos2][length] == 1);
        if (length == 1) {
            dp[pos1][pos2][length] = ((s1.charAt(pos1) == s2.charAt(pos2)) ? 1 : 0);
            return s1.charAt(pos1) == s2.charAt(pos2);
        }
        boolean res1, res2, res3, res4;
        for (int i = 1; i < length; i++) {
            res1 = (dp[pos1][pos2][i] != -1) ? (dp[pos1][pos2][i] == 1) : isScramble(s1, s2, pos1, pos2, i);
            res2 = (dp[pos1+i][pos2+i][length-i] != -1) ? (dp[pos1+i][pos2+i][length-i] == 1) : isScramble(s1, s2, pos1+i, pos2+i, length-i);
            res3 = (dp[pos1][pos2+i][length-i] != -1) ? (dp[pos1][pos2+i][length-i] == 1) : isScramble(s1, s2, pos1, pos2+i, length-i);
            res4 = (dp[pos1+length-i][pos2][i] != -1) ? (dp[pos1+length-i][pos2][i] == 1) : isScramble(s1, s2, pos1+length-i, pos2, i);
            if ((res1 && res2) || (res3 && res4)) {
                dp[pos1][pos2][length] = 1;
                return true;
            }
        }
        dp[pos1][pos2][length] = 0;
        return false;
    }
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 1; k <= n; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return isScramble(s1, s2, 0, 0, n);
    }
}
