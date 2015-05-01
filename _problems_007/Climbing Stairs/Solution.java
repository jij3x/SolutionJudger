public class Solution {
    public int climbStairs(int n) {
        int p = 1, q = 1;
        for (int i = 2; i <= n; i++) {
            int temp = q;
            q += p;
            p = temp;
        }
        return q;
    }
}