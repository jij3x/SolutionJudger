public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += this.twoSumSmaller(nums, i + 1, nums.length - 1, target - nums[i]);
        }
        return total;
    }

    private int twoSumSmaller(int[] nums, int start, int end, int target) {
        int total = 0;
        while (start < end) {
            if (nums[start] + nums[end] >= target) {
                end--;
            } else {
                total += (end - start++);
            }
        }
        return total;
    }
}