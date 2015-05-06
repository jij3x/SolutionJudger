public class Solution {
    public int maxPoints(Point[] points) {
        if (points.length == 0)
            return 0;
        int max = 1;
        HashMap<Double, Integer> memo = new HashMap<Double, Integer>();
        for (int i = 0; i < points.length - 1; i++) {
            Point start = points[i];
            int same = 1, perpendicular = 0;
            for (int j = i + 1; j < points.length; j++) {
                Point curr = points[j];
                if (curr.x == start.x && curr.y == start.y) {
                    same++;
                } else if (curr.x == start.x) {
                    perpendicular++;
                } else {
                    double slope = (double) (curr.y - start.y) / (curr.x - start.x);
                    if (slope == 0.0)
                        slope = 0.0;

                    if (memo.containsKey(slope))
                        memo.put(slope, memo.get(slope) + 1);
                    else
                        memo.put(slope, 1);
                }
            }

            int localMax = perpendicular;
            for (Integer c : memo.values())
                localMax = Math.max(localMax, c);

            max = Math.max(max, localMax + same);
            memo.clear();
        }
        return max;
    }
}