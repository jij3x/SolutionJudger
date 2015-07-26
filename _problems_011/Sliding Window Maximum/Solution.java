public class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            int[] store = {};
            return store;
        }
        if (nums.length <= k) {
            int[] store = new int[1];
            Arrays.sort(nums);
            store[0] = nums[nums.length - 1];
            return store;
        }
        int[] store = new int[nums.length - k + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.add(-nums[i]);
        }
        store[0] = -pq.peek();
        for (int i = k; i < nums.length; i++) {
            pq.remove(-nums[i - k]);
            pq.add(-nums[i]);
            store[i-k+1] = -pq.peek();
        }
        return store;
    }

}
