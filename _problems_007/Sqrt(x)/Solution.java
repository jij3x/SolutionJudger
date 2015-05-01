public class Solution {
    public int sqrt(int x) {
        if (x <= 1) return x;
        int L = 0, H = x/2;
        while (L < H) {
            int M = (L+H+1)/2;
            long sqrM = (long)M*M;
            if (sqrM <= x) {
                L = M;
            } else {
                H = M-1;
            }
        }
        return L;
    }
}