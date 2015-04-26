public class Solution {
    public int numDecodings(String num) {
        int n = num.length();
        if (n == 0) return 0;
        int L = 1;
        int R = (num.charAt(0) == '0') ? 0 : 1;
        for (int i = 1; i < n; i++) {
          int a = num.charAt(i-1) - '0';
          int b = num.charAt(i) - '0';
          int newR = (b == 0) ? 0 : R;
          if (a == 1 || (a == 2 && b <= 6))
              newR += L;
          L = R;
          R = newR;
        }
        return R;
    }
}