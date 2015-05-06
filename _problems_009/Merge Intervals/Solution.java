public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        int n = intervals.size();
        ArrayList<Interval> ret = new ArrayList<Interval>();
        if (n == 0)
            return ret;
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval interval, Interval otherInterval) {
                return interval.start - otherInterval.start;
            }
        });
        ret.add(intervals.get(0));
        int end = intervals.get(0).end;
        for (int i = 1; i < n; i++) {
            int s = intervals.get(i).start, e = intervals.get(i).end;
            if (s <= end) {
                ret.get(ret.size() - 1).end = Math.max(e, end);
            } else {
                ret.add(intervals.get(i));
            }
            end = ret.get(ret.size() - 1).end;
        }
        return ret;
    }
}