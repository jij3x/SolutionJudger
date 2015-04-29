public class Solution {
    public boolean canJump(int[] A) {
        int i = 0, reach = 0;
        while (i <= reach) {
            reach = Math.max(reach, i+A[i]);
            if (reach >= A.length-1) return true;
            i++;
        }
        return false;
    }
}