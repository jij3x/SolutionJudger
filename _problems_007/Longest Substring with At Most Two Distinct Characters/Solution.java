// O(N), only use N steps.
public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Set<Character> used = new HashSet<>();
        int i = 0, last = 0, maxLen = 0;
        for (int j = 0; j < s.length(); j++) {
            if (!used.contains(s.charAt(j)) && used.size() == 2) {
                i = last;
                used.clear();
                used.add(s.charAt(i));
            }
            used.add(s.charAt(j));
            if (s.charAt(j) != s.charAt(last)) {
                last = j;
            }
            maxLen = Math.max(j - i + 1, maxLen);
        }
        return maxLen;
    }
}