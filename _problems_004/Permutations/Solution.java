public class Solution {
    public List<List<Integer>> permute(int[] num) {
        ArrayList<List<Integer>> ret = new ArrayList<List<Integer>>();
        int n = num.length;
        boolean[] used = new boolean[n];
        int[] index = new int[n];
        permute(num, used, index, 0, ret);
        return ret;
    }
    void permute(int[] num, boolean used[], int index[], int pos, ArrayList<List<Integer>> ret) {
        int n = num.length;
        if (pos == n) {
            ArrayList<Integer> ans = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                ans.add(num[index[i]]);
            }
            ret.add(ans);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            used[i] = true;
            index[pos] = i;
            permute(num, used, index, pos+1, ret);
            used[i] = false;
        }
    }
}
