public class Solution {
    // O(MN) solution using stack.
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int maxArea = 0;
        int[] cache = new int[n+1];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == '0') cache[c] = 0;
                else cache[c]++;
            }
            maxArea = Math.max(maxArea, largestRectangleAreaInHistogram(cache));
        }
        return maxArea;
    }
    public int largestRectangleAreaInHistogram(int[] height) {
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
