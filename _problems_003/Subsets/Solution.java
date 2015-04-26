public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        ArrayList<List<Integer>> ret = new ArrayList<List<Integer>>();
        Arrays.sort(S);
        int m = S.length;
        int n = (1 << m);
        for (int i = 0; i < n; i++) {
            ret.add(getBinary(S, i));
        }
        return ret;
    }
    ArrayList<Integer> getBinary(int[] S, int binary) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        int m = S.length;
        for (int j = 0, k = 0; j < m; j++, k++) {
            if ((binary & 1) == 1) {
                ret.add(S[j]);
            }
            binary >>= 1;
        }
        return ret;
    }
}
