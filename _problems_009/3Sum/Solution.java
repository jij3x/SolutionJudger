public class Solution {
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        ArrayList<List<Integer>> ans = new ArrayList<List<Integer>>();
        int n = num.length;
        for (int i = 0; i < n-2; ) {
            int target = -num[i];
            int j = i+1, k = n-1;
            while (j < k) {
                int sum = num[j]+num[k];
                if (sum < target) {
                    j++;
                } else if (sum > target) {
                    k--;                
                } else {
                    ArrayList<Integer> triplets = new ArrayList<Integer>();
                    triplets.add(new Integer(num[i]));
                    triplets.add(new Integer(num[j]));
                    triplets.add(new Integer(num[k]));
                    ans.add(triplets);
                    while (j < n && num[j] == triplets.get(1)) j++;
                    while (k >= 0 && num[k] == triplets.get(2)) k--;
                }
            }
            int prev = num[i];
            while (i < n-2 && num[i] == prev) i++;
        }
        return ans;
    }
}
