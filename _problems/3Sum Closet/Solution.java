public class Solution {
    public int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        int n = num.length;
        assert(n >= 3);
        int closestSum = num[0]+num[1]+num[2];
        int minDelta = Math.abs(target - closestSum);
        for (int i = 0; i < n-2; i++) {
            int j = i+1, k = n-1;
            int sub = target - num[i];
            while (j < k) {
                int sum = num[j] + num[k];
                if (sum < sub) {
                    j++;
                } else {
                    k--;
                }
                int delta = Math.abs(sub - sum);
                if (delta < minDelta) {
                    minDelta = delta;
                    closestSum = sum + num[i];
                }
            }
        }
        return closestSum;
    }
}