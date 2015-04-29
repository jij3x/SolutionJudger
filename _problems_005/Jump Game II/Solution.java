public class Solution {
    public int jump(int[] a) {
        int reach = 0;
        int last = 0;
        int steps = 0;
       
        while (reach < a.length-1) {
            int max = reach;
            for (int i=last; i<=reach && i<a.length; i++) {
                max = Math.max(max, i + a[i]);
            }
            steps++;
            last = reach + 1;
            reach = max;
        }
       
        return steps;
    }
}
