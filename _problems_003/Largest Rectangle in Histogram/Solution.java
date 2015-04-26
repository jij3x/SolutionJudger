public class Solution {
    public int largestRectangleArea(int[] height) {
        int max_area = 0;
        int n = height.length;
        int[] h = new int[n+1];
        for (int i = 0; i < n; i++) h[i] = height[i];
        h[n] = 0;
        int[] rS = new int[n+1];
        int[] lS = new int[n+1];
        int sp = 0;
        for (int i=0 ; i<=n ; i++)
        {
            lS[sp] = i;
            while (sp > 0 && h[rS[sp-1]] > h[i])
            {
              --sp;
              max_area = Math.max(max_area, (i-lS[sp])*h[rS[sp]]);
            }
            rS[sp] = i;
            ++sp;
        }
        return max_area;
    }
}