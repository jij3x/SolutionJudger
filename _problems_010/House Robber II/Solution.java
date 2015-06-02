public class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int p0 = 0, p1 = 0, p2 = 0, p3 = nums[0], np0, np1;
        for (int i = 1; i < n; ++i) {
            np0 = Math.max(p0, p2);
            np1 = Math.max(p1, p3);
            p2 = p0 + nums[i];
            p3 = p1 + nums[i];
            p0 = np0;
            p1 = np1;
        }
        return Math.max(p0, Math.max(p1, p2));
    }
}