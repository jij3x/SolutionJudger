public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        ArrayList<List<Integer>> ret = new ArrayList<List<Integer>>();
        ArrayList<Integer> num = new ArrayList<Integer>();
        combine(num, 1, n, k, ret);
        return ret;
    }
    void combine(ArrayList<Integer> num, int val, int n, int k, ArrayList<List<Integer> > ret) {
        if (num.size() == k) {
            ret.add(new ArrayList<Integer>(num));
            return;
        }
        for (int i = val; i <= n; i++) {
            num.add(i);
            combine(num, i+1, n, k, ret);
            num.remove(num.size()-1);
        }
    }
}
