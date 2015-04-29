public class Solution {
    public int longestConsecutive(int[] num) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        int maxLen = 1;
        for (int i = 0; i < num.length; i++) {
            if (map.containsKey(num[i])) continue;
            int prev = (map.containsKey(num[i]-1) ? map.get(num[i]-1) : 0);
            int next = (map.containsKey(num[i]+1) ? map.get(num[i]+1) : 0);
            int total = prev + next + 1;
            maxLen = Math.max(total, maxLen);
            map.put(num[i], total);
            map.put(num[i]+next, total);
            map.put(num[i]-prev, total);
        }
        return maxLen;
    }
}