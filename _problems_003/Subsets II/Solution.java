public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] S) {
        Arrays.sort(S);
        ArrayList<List<Integer> > ret = new ArrayList<List<Integer>>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        dfs(S, 0, ans, ret);
        return ret;
    }
    void dfs(int[] S, int start, ArrayList<Integer> ans, ArrayList<List<Integer>> ret) {
        int n = S.length;
        ret.add(new ArrayList<Integer>(ans));
        if (start == n) return;
        
        for (int i = start; i < n; ) {
            ans.add(S[i]);
            dfs(S, i+1, ans, ret);
            ans.remove(ans.size()-1);
            int j = i;
            while (i < n && S[i] == S[j]) i++;
        }
    }
}