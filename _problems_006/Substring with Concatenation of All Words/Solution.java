public class Solution {
    public List<Integer> findSubstring(String S, List<String> L) {
        List<Integer> ret = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int m = L.size();
        for (int i = 0; i < m; i++) {
            Integer x = (Integer) map.get(L.get(i));
            int val = (x == null) ? 0 : x;
            map.put(L.get(i), val + 1);
        }
        int n = S.length();
        int p = L.get(0).length();
        for (int i = 0; i <= n - p * m; i++) {
            int j = i;
            HashMap<String, Integer> map2 = new HashMap<String, Integer>(map);
            while (j <= n - p) {
                String str = S.substring(j, j + p);
                Integer x = (Integer) map2.get(str);
                if (x == null || x == 0)
                    break;
                map2.put(str, x - 1);
                j += p;
                if (j - i == p * m) {
                    ret.add(i);
                    break;
                }
            }
        }
        return ret;
    }
}