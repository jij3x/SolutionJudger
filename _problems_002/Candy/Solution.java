public class Solution {
    public int candy(int[] ratings) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int n = ratings.length;
        int low = 0;
        int sum = 0;
        int[] f = new int[n];
        f[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                f[i] = f[i - 1] + 1;
            } else {
                f[i] = 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                if (f[i + 1] + 1 > f[i]) f[i] = f[i + 1] + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            sum += f[i];
        }
        return sum;
    }
}
