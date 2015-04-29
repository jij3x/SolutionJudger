public class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int L = 0, R = n-1;
        int largestArea = 0;
        while (L < R) {
            int lHeight = height[L], rHeight = height[R];
            int area = Math.min(lHeight, rHeight) * (R - L);
            if (area > largestArea)
                largestArea = area;
            if (lHeight <= rHeight)
                while(L < R && height[L] <= lHeight) L++;
            if (lHeight >= rHeight)
                while (L < R && height[R] <= rHeight) R--;
        }
        return largestArea;
    }
}
