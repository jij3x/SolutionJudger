public class Solution {
    public int hIndex(int[] citations) {
        for (int s = 0, e = citations.length - 1; s <= e;) {
            int m = (s + e) / 2, h = citations.length - m;
            if (citations[m] >= h && (m == 0 || citations[m - 1] < h + 1))
                return h;
            else if (citations[m] < h)
                s = m + 1;
            else
                e = m - 1;
        }
        return 0;
    }
}
