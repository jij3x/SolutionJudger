public class Solution {
    public int trailingZeroes(int n) {
        int s = 0;
        while ((n /= 5) > 0) s += n;
        return s;
    }
}