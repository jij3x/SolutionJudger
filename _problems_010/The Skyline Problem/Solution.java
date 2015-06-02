public class Solution {
    public class Building {
        public int l, r;
        public int h;

        public Building() {
            l = 0;
            r = 0;
            h = 0;
        }

        public Building(int _l, int _r, int _h) {
            l = _l;
            r = _r;
            h = _h;
        }
    }

    private List<int[]> skylineHelper(List<Building> buildings, int lo, int hi) {
        List<int[]> res = new ArrayList<>();
        if (lo == hi) {
            res.add(new int[] { buildings.get(lo).l, buildings.get(lo).h });
            res.add(new int[] { buildings.get(lo).r, 0 });
            return res;
        }

        int mid = (lo + hi) / 2;
        List<int[]> a = skylineHelper(buildings, lo, mid);
        List<int[]> b = skylineHelper(buildings, mid + 1, hi);

        // Now is the key part: merging
        int sizea = a.size();
        int sizeb = b.size();
        int i = 0, j = 0;
        int last_ah = 0, last_bh = 0;

        while (i < sizea || j < sizeb) {
            int[] temp = new int[2];
            int[] atop = i < sizea ? a.get(i) : new int[] { Integer.MAX_VALUE, 0 };
            int[] btop = j < sizeb ? b.get(j) : new int[] { Integer.MAX_VALUE, 0 };

            if (atop[0] < btop[0]) {
                temp[0] = atop[0];
                temp[1] = Math.max(atop[1], last_bh);
                last_ah = atop[1];
                i++;
            } else if (atop[0] > btop[0]) {
                temp[0] = btop[0];
                temp[1] = Math.max(btop[1], last_ah);
                last_bh = btop[1];
                j++;
            } else {
                temp[0] = atop[0];
                temp[1] = Math.max(atop[1], btop[1]);
                last_ah = atop[1];
                last_bh = btop[1];
                i++;
                j++;
            }
            if (res.isEmpty() || res.get(res.size() - 1)[1] != temp[1])
                res.add(temp);
        }
        return res;
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        int nbs = buildings.length;
        if (nbs == 0)
            return new ArrayList<>();
        List<Building> b = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            b.add(new Building(buildings[i][0], buildings[i][1], buildings[i][2]));
        }

        List<int[]> result = skylineHelper(b, 0, nbs - 1);

        List<List<Integer>> r = new ArrayList<List<Integer>>();
        for (int[] arr : result) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            row.add(arr[0]);
            row.add(arr[1]);
            r.add(row);
        }
        return r;
    }
}