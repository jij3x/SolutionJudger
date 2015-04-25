public class Solution {
    public List<List<Integer>> fourSum(int[] num, int target) {
        int n = num.length;
        Arrays.sort(num);
        ArrayList<List<Integer>> quads = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; ) {
            for (int j = i+1; j < n; ) {
                int sum1 = num[i] + num[j];
                int k = j+1, l = n-1;
                while (k < l) {
                    int sum2 = num[k] + num[l];
                    int sum = sum1 + sum2;
                    if (sum > target) {
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        ArrayList<Integer> quad = new ArrayList<Integer>();
                        quad.add(num[i]); quad.add(num[j]); quad.add(num[k]); quad.add(num[l]);
                        quads.add(quad);
                        int num1 = num[k];
                        while (k < l && num[k] == num1) k++;
                        int num2 = num[l];
                        while (k < l && num[l] == num2) l--;
                    }
                }
                int num1 = num[j];
                while (j < n && num[j] == num1) j++;
            }
            int num1 = num[i];
            while (i < n && num[i] == num1) i++;
        }
        return quads;
    }
}
