public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] arr, int k, int l) {
        if (arr == null || arr.length == 0 || k < 1) {
            return false;
        }
        TreeSet<Integer> bst = new TreeSet<>();
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            Integer pred = bst.floor(num);
            Integer succ = bst.ceiling(num);
            if (pred != null && Math.abs((long)pred - num) <= l) {
                return true;
            }
            if (succ != null && Math.abs((long)succ - num) <= l) {
                return true;
            }
            bst.add(num);
            if (i >= k) {
                bst.remove(arr[i - k]);
            }
        }
        return false;
    }
}