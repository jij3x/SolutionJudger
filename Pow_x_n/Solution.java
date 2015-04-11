public class Solution {
    public double pow(double x, int n) {
        if (n < 0) {
            if (n == -2147483648) {
                return 1.0/(x*pow(x, 2147483647));
            }
            return 1.0/pow(x, -n);
        }
        double p = x;
        double ret = 1.0;
        while (n > 0) {
            if ((n & 1) == 1) {
                ret *= p;
            }
            n >>= 1;
            p *= p;
        }
        return ret;
    }
}