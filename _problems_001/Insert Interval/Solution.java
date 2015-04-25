public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int n = intervals.size();
        ArrayList<Interval> ret = new ArrayList<Interval>();
        for (int i = 0; i < n; i++) {
            int s1 = newInterval.start, e1 = newInterval.end, s2 = intervals.get(i).start, e2 = intervals.get(i).end;
            if (e1 < s2) {
                ret.add(newInterval);
                while (i < n) {
                    ret.add(intervals.get(i));
                    i++;
                }
                return ret;
            } else if (s1 > e2) {
                ret.add(intervals.get(i));
            } else {    // intersect
                newInterval.start = Math.min(s1, s2);
                newInterval.end = Math.max(e1, e2);
            }
        }
        ret.add(newInterval);
        return ret;
    }
}
