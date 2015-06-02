public class Solution {

    List<List<Integer>> result = new ArrayList<List<Integer>>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        getCombination(k, n, 1, new Stack<Integer>());
        return result;
    }

    public void getCombination(int k, int n, int start, Stack<Integer> combination) {

        if (k == 0 && n == 0) {
            @SuppressWarnings("unchecked")
            java.util.Vector<Integer> temp = (java.util.Vector<Integer>) combination.clone();
            result.add(temp);
            return;
        }

        start = Math.max(start, n - 45 + (10 - k) * (11 - k) / 2);
        int end = (int) ((double) n / k - (k - 1) / 2.0);

        for (int i = start; i <= end; i++) {
            combination.push(i);
            getCombination(k - 1, n - i, i + 1, combination);
            combination.pop();
        }
    }
}