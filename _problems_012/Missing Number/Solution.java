public class Solution {
    public int missingNumber(int[] nums) {
        int check = 0;
        for (int i=0; i<nums.length; i++) {
            check ^= nums[i] ^ i+1;
        }
        return check;
    }
}