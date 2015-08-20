public class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals == null)
            return false;

        // Sort the intervals by start time
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        for (int i = 1; i < intervals.size(); i++)
            if (intervals.get(i).start < intervals.get(i - 1).end)
                return false;

        return true;
    }
}
