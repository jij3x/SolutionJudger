import java.math.BigInteger;

public class Solution {
    public int numDistinct(String S, String T) {
        int m = S.length(), n = T.length();
        BigInteger[][] dp = new BigInteger[m+1][n+1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = BigInteger.ONE;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = BigInteger.ZERO;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (S.charAt(i-1) == T.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j].add(dp[i-1][j-1]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        if (dp[m][n].compareTo(new BigInteger("2147483647")) == -1) {
            return dp[m][n].intValue();
        } else {
            System.out.print(dp[m][n]);
            return -1;
        }
    }
}