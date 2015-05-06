public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        List<Integer> minimums = new ArrayList<>();
        for (int i = 0; i < n; i++) minimums.add(0);
        minimums.set(0, triangle.get(0).get(0));
        int ret = minimums.get(0);
        for (int i = 1; i < n; i++) {
            ret = Integer.MAX_VALUE;
            int runningMin = minimums.get(0);
            for (int j = 0; j <= i; j++) {
                int left = (j >= 1) ? runningMin : Integer.MAX_VALUE;
                int right = (j <= i-1) ? minimums.get(j) : Integer.MAX_VALUE;
                runningMin = minimums.get(j);
                minimums.set(j, Math.min(left, right) + triangle.get(i).get(j));
                ret = Math.min(ret, minimums.get(j));
            }
        }
        return ret;
    }
}
