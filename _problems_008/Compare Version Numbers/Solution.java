public class Solution {
    public int compareVersion(String a, String b) {
        String[] ap = a.split("\\.");
        String[] bp = b.split("\\.");
        for (int i = 0; i < Math.max(ap.length, bp.length); i++) {
            int api = i < ap.length ? Integer.parseInt(ap[i]) : 0;
            int bpi = i < bp.length ? Integer.parseInt(bp[i]) : 0;
            if (api > bpi) return 1;
            if (api < bpi) return -1;
        }
        return 0;
    }
}