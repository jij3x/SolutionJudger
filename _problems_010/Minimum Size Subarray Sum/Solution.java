public class Solution {
    public int minSubArrayLen(int[] nums, int s) {
        int minLen = nums.length + 1;
        for (int sum = 0, start = 0, end = 0; end < nums.length;) {
            sum += nums[end++];
            while (sum >= s) {
                minLen = Math.min(minLen, end - start);
                sum -= nums[start++];
            }
        }
        return minLen == nums.length + 1 ? 0 : minLen;
    }
}