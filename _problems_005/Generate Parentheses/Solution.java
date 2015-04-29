public class Solution {
    void dfs(int n, int open, int close, String s, ArrayList<String> ans) {
        if (close > open) return;
        if (open > n) return;
        if (open == close && open == n) {
            ans.add(s);
            return;
        }
        
        dfs(n, open+1, close, s + "(", ans);
        dfs(n, open, close+1, s + ")", ans);
    }
    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> ans = new ArrayList<String>();
        String s = new String();
        dfs(n, 0, 0, s, ans);
        return ans;
    }
}
