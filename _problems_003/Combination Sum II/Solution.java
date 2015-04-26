public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        ArrayList<Integer> index = new ArrayList<Integer>();
        index.add(-1);
        ArrayList<List<Integer>> ret = new ArrayList<List<Integer>>();
        combinationSum(candidates, target, 0, index, ret);
        return ret;
    }
    void combinationSum(int[] candidates, int target, int sum, ArrayList<Integer> index, ArrayList<List<Integer>> ans) {
        int n = candidates.length;
        int k = index.size();
        if (sum > target) {
            return;
        }
        if (sum == target) {
            ans.add(generateCombination(candidates, index));
            return;
        }
        
        for (int i = index.get(k-1) + 1; i < n; ) {
            index.add(i);
            combinationSum(candidates, target, sum + candidates[i], index, ans);
            index.remove(k);
            int prev = candidates[i];
            while (i < n && candidates[i] == prev) i++;
        }
    }
    ArrayList<Integer> generateCombination(int[] candidates, ArrayList<Integer> index) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        int k = index.size();
        for (int i = 1; i < k; i++) {
            ret.add(candidates[index.get(i)]);
        }
        return ret;
    }
}