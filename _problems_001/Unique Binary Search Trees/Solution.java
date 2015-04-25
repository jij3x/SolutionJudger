public class Solution {
    private int[] memo;
    public Solution() {
        memo = new int[128];
    }
    public int numTrees(int n) {
        // base case
        if(n <= 1){return 1;}
        if (memo[n] != 0) return memo[n];
        // recursion
        int sum = 0;
        for(int i = 1;i <= n;i++)
            sum += numTrees(i-1-0) * numTrees(n-i);
        return memo[n] = sum;
    }
}