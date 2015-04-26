public class Solution {
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < (1<<n); i++)
            ret.add(i^(i>>1));
        return ret;
    }
}
