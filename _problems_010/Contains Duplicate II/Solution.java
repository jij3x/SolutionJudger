public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<Integer>();

        for(int i=0;i<nums.length && i<=k;i++){
            if(!set.add(nums[i])){
                return true;
            }
        }

        for(int i=k+1;i<nums.length;i++){
            set.remove(nums[i-k-1]);
            if(!set.add(nums[i])){
                return true;
            }
        }
        return false;
    }
}