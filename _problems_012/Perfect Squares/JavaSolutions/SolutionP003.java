public class Solution {
    public int numSquares(int n) {
        while (n % 4 == 0)
            n /= 4;
        if (n % 8 == 7)
            return 4;
        int a = -1, b = (int) Math.sqrt(n);
        n -= b * b;
        b += b + 1;
        while (a <= b) {
            if (n < 0)
                n += b -= 2;
            else if (n > 0)
                n -= a += 2;
            else
                return a < 0 ? 1 : 2;
        }
        return 3;
    }
}
