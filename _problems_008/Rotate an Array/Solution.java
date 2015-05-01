public class Solution {
    private void reverse(int[] nums, int start, int end) {
        for (--end; start < end; ++start, --end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
        }
    }
    
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        if (k == 0) return;
        reverse(nums, 0, n-k);
        reverse(nums, n-k, n);
        reverse(nums, 0, n);
    }
}