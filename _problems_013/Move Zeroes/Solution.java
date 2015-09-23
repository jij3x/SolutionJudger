public class Solution {
    public void moveZeroes(int[] nums) {
        int tail = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                nums[tail++] = nums[i];
        }
        while (tail < nums.length)
            nums[tail++] = 0;
    }
}
