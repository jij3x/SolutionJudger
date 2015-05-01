public class Solution {
    public int findPeakElement(int[] a) {
        int l = 0, r = a.length;
        if (r == 1) return 0;
        if (a[1] < a[0]) return 0;
        if (a[r - 2] < a[r - 1]) return r - 1;
        for (;;) {
          if (r - l == 3) return l + 1;
          if (r - l == 4) return a[l + 1] > a[l + 2] ? l + 1 : l + 2;
          int m = (l + r) >> 1;
          if (a[m] > a[m - 1] && a[m] > a[m + 1]) return m;
          if (a[m] < a[m - 1]) r = m + 1;
          else l = m;
        }
    }
}