public class Solution {
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
       
        for(int i = 0;i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c == '(') {
                stack.add(i);
            } else if (!stack.isEmpty()) {
                int m = stack.pop();
                if (m > 0 && dp[m-1] != -1)
                    dp[i] = Math.min(dp[m-1],m);
                else
                    dp[i] = m;
               
                int len = i-dp[i]+1;
                if(len > max) {
                    max=len;
                }
            }
        }       
        return max;
    }
}
